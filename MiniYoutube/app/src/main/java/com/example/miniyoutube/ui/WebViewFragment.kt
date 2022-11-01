package com.example.miniyoutube.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.miniyoutube.R
import com.example.miniyoutube.WebviewFragmentArgs
import com.example.miniyoutube.databinding.FragmentWebviewBinding
import com.example.miniyoutube.viewmodel.VideoPlayerViewModel
import com.example.miniyoutube.viewmodel.VideoPlayerViewModelFactory

class WebViewFragment : Fragment() {
    private lateinit var binding:FragmentWebviewBinding
    private lateinit var videoPlayerViewModel: VideoPlayerViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        videoPlayerViewModel = ViewModelProvider(this)[VideoPlayerViewModel::class.java]
        binding.videoPlayer = videoPlayerViewModel
        binding.lifecycleOwner = this

        val args = WebviewFragmentArgs.fromBundle(requireArguments()).videoLink
        val factory = VideoPlayerViewModelFactory(args)
        Toast.makeText(activity, args.toString(), Toast.LENGTH_SHORT).show()
        videoPlayerViewModel = ViewModelProvider(this,factory)[VideoPlayerViewModel::class.java]
        binding.videoPlayer = videoPlayerViewModel


        return binding.root
    }
}