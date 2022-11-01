package com.example.miniyoutube.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.miniyoutube.R
import com.example.miniyoutube.databinding.FragmentPlayBinding
import com.example.miniyoutube.viewmodel.VideoViewModel
import com.example.miniyoutube.viewmodel.VideoViewModelFactory


class VideoDetailFragment : Fragment() {
private  lateinit var binding: FragmentPlayBinding
private lateinit var viewModel:VideoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater,container,false)
        val application = requireNotNull(activity).application

        binding.lifecycleOwner = this

        val args = VideoDetailFragmentArgs.fromBundle(requireArguments()).selectPhoto
        val factory = VideoViewModelFactory(args)
        Toast.makeText(activity, args.toString(), Toast.LENGTH_SHORT).show()
         viewModel = ViewModelProvider(this,factory)[VideoViewModel::class.java]
        binding.viewModel = viewModel

        binding.playBtn.setOnClickListener {
            findNavController().navigate(VideoDetailFragmentDirections.actionVideoFragmentToWebviewFragment(args.video_url.toString()))
        }

             return binding.root
         }
}