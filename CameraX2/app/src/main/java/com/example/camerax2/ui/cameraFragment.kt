package com.example.camerax2.ui

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.request.RequestOptions
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.example.camerax2.R
import com.example.camerax2.databinding.FragmentCameraBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class cameraFragment : Fragment() {


    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private  var cameraSelector: CameraSelector? = null
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater,container,false)
        val registrationManager = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // returns boolean representind whether the
            // permission is granted or not
            if (isGranted) {
                // permission granted continue the normal workflow of app
                startcamera()
                Log.i("DEBUG", "permission granted")
            } else {
                // if permission denied then check whether never ask
                // again is selected or not by making use of
                // !ActivityCompat.shouldShowRequestPermissionRationale(
                // requireActivity(), Manifest.permission.CAMERA)
                Log.i("DEBUG", "permission denied")
            }
        }

        binding.imageButton.setOnClickListener {
            takePhoto()
        }
//        binding.cameraSwithc.setOnClickListener {
//          cameraSelector =  CameraSelector.DEFAULT_FRONT_CAMERA
//        }
        cameraExecutor  = Executors.newSingleThreadExecutor()
        registrationManager.launch(Manifest.permission.CAMERA)
        return binding.root
    }


    private fun takePhoto(){
        val imageCapture = imageCapture?:return

        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

        val contentValue = ContentValues().apply{
            put(MediaStore.MediaColumns.DISPLAY_NAME,name.toString())
            put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraXImage")
            }
        }
//
            val outPutOption = ImageCapture.OutputFileOptions.Builder(context?.contentResolver!!,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValue
            ).build()


        imageCapture.takePicture(
            outPutOption,
            ContextCompat.getMainExecutor(requireContext()),
            object :ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(File("Pictures/CameraXImage"))
                    setThumnail(savedUri)
                    val path = "Photo stored in ${outputFileResults.savedUri}"
                    Toast.makeText(activity, path, Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(activity, "image cannot store ", Toast.LENGTH_SHORT).show()
                }

            }
        )


    }


    private fun startcamera(){
//        if(checkPermisssion()){
            val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

            cameraProviderFuture.addListener({
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build()  .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

                 imageCapture = ImageCapture.Builder().build()

                 cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector!!,preview,imageCapture)
                }catch (exc: Exception) {
                    Log.e("TAG", "Use case binding failed", exc)
                }
            },ContextCompat.getMainExecutor(requireContext()))

    }

    private  fun checkPermisssion():Boolean =
        REQUEST_PERMISSSION.all {permission->
            ContextCompat.checkSelfPermission(requireActivity(),permission) == PackageManager.PERMISSION_GRANTED
        }



    private fun setThumnail(uri: Uri){

        binding?.imageSave?.let { photoViewButton->
            photoViewButton.post{
                photoViewButton.setPadding(resources.getDimension(R.dimen.stroke_small).toInt())
                Glide.with(photoViewButton).load(uri).apply(RequestOptions.circleCropTransform())
                    .into(photoViewButton)
            }

        }

    }

    fun updateCamera(){

    }

    companion object{
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private  val REQUEST_PERMISSSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}