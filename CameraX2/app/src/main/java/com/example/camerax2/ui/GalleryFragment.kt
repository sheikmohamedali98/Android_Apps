package com.example.camerax2.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.camerax2.R
import com.example.camerax2.adapter.GalleryAdapter
import com.example.camerax2.databinding.FragmentGalleryBinding
import java.io.File
import java.nio.file.Paths


class GalleryFragment : Fragment() {

private lateinit var binding: FragmentGalleryBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentGalleryBinding.inflate(layoutInflater)
//        val directory = File("")

//        val path = Paths.get("").toAbsolutePath().toString()
        val listOfFile: MutableList<File> = ArrayList()



        File("/sdcard/Pictures/CameraXImage/").listFiles()?.forEach {
            listOfFile.add(it)
        }
        Toast.makeText(activity, listOfFile.toString(), Toast.LENGTH_SHORT).show()


        println("\n\n\n\n${listOfFile}\n\n\n")
//        letovi.forEach{ Log.i("Pic Path","file -> ${it}") }


        binding.backBtn.setOnClickListener {
            Toast.makeText(activity, "back Navigate", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

//        val files = File("//media/external/images/media/").listFiles() as? Array<File>
//        println("\n\n\n\n${files.toString()}\n\n\n")

        val adapter = GalleryAdapter(listOfFile.toTypedArray().reversedArray())

        binding.viewPager.adapter = adapter

        return binding.root
    }


}