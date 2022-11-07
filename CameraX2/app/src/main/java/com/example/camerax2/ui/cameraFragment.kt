package com.example.camerax2.ui

import android.Manifest
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import androidx.annotation.RequiresApi
import com.bumptech.glide.request.RequestOptions
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.camerax2.R
import com.example.camerax2.databinding.FragmentCameraBinding
import com.example.camerax2.viewmodel.CameraViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class cameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector? = null
    private lateinit var viewModel:CameraViewModel
    private  var flashMode:Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        viewModel = ViewModelProvider(this)[CameraViewModel::class.java]

        binding.lifecycleOwner = this

        val registrationManager =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
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
                    Snackbar.make(
                        binding.root,
                        "The camera permission is necessary",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                }
            }
         flashMode = ImageCapture.FLASH_MODE_OFF

        viewModel.preview.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

//            binding.imageSave = it
        })
        binding.imageButton.setOnClickListener {
            takePhoto()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                autoFlash()
            }
        }

        binding.switchbtn.setOnClickListener {
            Toast.makeText(activity, cameraSelector.toString(), Toast.LENGTH_SHORT).show()
            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }
            startcamera()
        }

        binding.imageSave.setOnClickListener {
            findNavController().navigate(R.id.action_cameraFragment_to_galleryFragment)
        }
        binding.flashBtn.setOnClickListener {
            Toast.makeText(activity, flashMode.toString(), Toast.LENGTH_SHORT).show()

            if(cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                flashMode = when (flashMode) {
                    ImageCapture.FLASH_MODE_OFF -> ImageCapture.FLASH_MODE_ON
                    ImageCapture.FLASH_MODE_ON -> ImageCapture.FLASH_MODE_AUTO
                    ImageCapture.FLASH_MODE_AUTO -> ImageCapture.FLASH_MODE_OFF
                    else -> {ImageCapture.FLASH_MODE_ON}
                }
            }
        }
        cameraExecutor = Executors.newSingleThreadExecutor()
        registrationManager.launch(Manifest.permission.CAMERA)
        return binding.root
    }


    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())

        val contentValue = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name.toString())
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraXImage")
            }
        }
//
        val outPutOption = ImageCapture.OutputFileOptions.Builder(context?.contentResolver!!,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValue
        ).build()


        imageCapture.takePicture(
            outPutOption,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                    val savedUri =
                        outputFileResults.savedUri ?: Uri.fromFile(File("Pictures/CameraXImage"))
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun autoFlash() {
        binding.root.postDelayed({
            binding.root.foreground = ColorDrawable(Color.WHITE)
            binding.root.postDelayed({
                binding.root.foreground = null
            }, 50)
        }, 100)
    }

    private fun startcamera() {
//        if(checkPermisssion()){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            }

//                .setFlashMode(ImageCapture.FLASH_MODE_OFF)
            Toast.makeText(context, flashMode.toString(), Toast.LENGTH_SHORT).show()
            imageCapture = ImageCapture.Builder().setFlashMode(flashMode).build()

            try {
                cameraProvider.unbindAll()
               val camera =  cameraProvider.bindToLifecycle(this, cameraSelector!!, preview, imageCapture)
//                if(camera.cameraInfo.hasFlashUnit()){
//                    camera.cameraControl.enableTorch(true)
//                }
            } catch (exc: Exception) {
                Log.e("TAG", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))

    }

//    private  fun checkPermisssion():Boolean =
//        REQUEST_PERMISSSION.all {permission->
//            ContextCompat.checkSelfPermission(requireActivity(),permission) == PackageManager.PERMISSION_GRANTED
//        }


    private fun setThumnail(uri: Uri) {

        binding?.imageSave?.let { photoViewButton ->
            photoViewButton.post {
                photoViewButton.setPadding(resources.getDimension(R.dimen.stroke_small).toInt())
                Glide.with(photoViewButton).load(uri).apply(RequestOptions.circleCropTransform())
                    .into(photoViewButton)
            }

        }

    }


    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUEST_PERMISSSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

}