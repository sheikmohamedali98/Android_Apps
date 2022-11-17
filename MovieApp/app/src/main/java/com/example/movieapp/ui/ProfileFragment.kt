package com.example.movieapp.ui

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentProfileBinding
import com.example.movieapp.viewModel.MovieProfileViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.io.File


class ProfileFragment : Fragment() {
    private val PROFILE_PICTURE_KEY = "PROFILE_PIC_PATH"
    private val SHARED_PREF = "UserPreference"

    private lateinit var  binding: FragmentProfileBinding
    private lateinit var viewModel:MovieProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[MovieProfileViewModel::class.java]
        sharedPreferences =  requireContext().getSharedPreferences(SHARED_PREF,Application.MODE_PRIVATE)

        binding.profileImage.setOnClickListener {
            Toast.makeText(activity, "profile clicked ", Toast.LENGTH_SHORT).show()
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
        //default
        //if old pic exist
        sharedPreferences.getString(PROFILE_PICTURE_KEY,"").also {
                path->
            path?.let{
                if (path.isNotEmpty()) {
                    //
                    val fileUri = Uri.fromFile(File(path))
                    binding.profileImage.setImageURI(fileUri)
                }
            }
        }


        return binding.root
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!

//                Toast.makeText(activity, fileUri.path.toString(), Toast.LENGTH_SHORT).show()

                sharedPreferences.edit().also {editor->
                    editor.putString(PROFILE_PICTURE_KEY, fileUri.path.toString())
                    editor.commit()
                }

                binding.profileImage.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(activity, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


}