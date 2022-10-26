package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.databinding.CardLayoutBinding
import com.example.newsapp.databinding.FragmentDetailBinding
import com.example.newsapp.viewmodel.DetailViewModel
import com.example.newsapp.viewmodel.DetailViewModelFactory


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel:DetailViewModel by lazy{
        var activity = requireNotNull(activity?.application)

        ViewModelProvider(this, DetailViewModelFactory(activity))[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)



//        val args:DetailFragmentArgs by navArgs()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(DetailViewModel.url)
        binding.webView.settings.javaScriptEnabled = true

        return binding.root
    }


}