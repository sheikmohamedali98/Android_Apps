package com.example.profilepicture

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.profilepicture.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var latestImageUri:Uri? = null
    private val preViewImage by lazy {
        binding.imagePreview
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClicker()
    }


//    private val photoPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//       if(it.resultCode  == Activity.RESULT_OK){
//           Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
//       }else{
//           Toast.makeText(this, "Image not Selected ", Toast.LENGTH_SHORT).show()
//       }
//    }

    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()){isSuccess->
        if(isSuccess){
            latestImageUri?.let { Uri->
             preViewImage.setImageURI(Uri)
            }
        }
    }

    private val selectImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()){uri:Uri?->
        uri?.let {
            preViewImage.setImageURI(uri)
        }
    }

    private  fun setClicker(){
        binding.takeImageButton.setOnClickListener {
            takeImage()
        }

        binding.selectImageButton.setOnClickListener {
            selectImageFrom()
        }

    }


    private fun takeImage(){

        lifecycleScope.launchWhenStarted {
            getTempFileUri().let { uri->
                latestImageUri = uri
                takeImageResult.launch(uri)
            }
        }
    }


    private fun selectImageFrom() = selectImageFromGallery.launch("image/*")

   private fun getTempFileUri():Uri{
        val tempFile = File.createTempFile("temp_image_file",".png",cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return  FileProvider.getUriForFile(this,"${BuildConfig.APPLICATION_ID}.provider",tempFile)
    }


}