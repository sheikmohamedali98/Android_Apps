package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailBinding
import com.example.newsapp.viewmodel.WebViewViewModel
import com.example.newsapp.viewmodel.WebViewViewModelFactory


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel:WebViewViewModel by lazy{
        var activity = requireNotNull(activity?.application)

        ViewModelProvider(this, WebViewViewModelFactory(activity))[WebViewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)



//        val args:DetailFragmentArgs by navArgs()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(WebViewViewModel.url)
        binding.webView.settings.javaScriptEnabled = true

        return binding.root
    }


}