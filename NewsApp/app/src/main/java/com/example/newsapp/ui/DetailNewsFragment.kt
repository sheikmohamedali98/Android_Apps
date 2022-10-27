package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentDetailNewsBinding
import com.example.newsapp.viewmodel.DetailViewModelFactory
import com.example.newsapp.viewmodel.DetailsViewModel


class DetailNewsFragment : Fragment() {


    private  lateinit var  binding: FragmentDetailNewsBinding
    private lateinit var  viewModel: DetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNewsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
//        lateinit var args: DetailNewsFragmentArgs


        val args = DetailNewsFragmentArgs.fromBundle(requireArguments()).newsInformation

        val viewModelFactory = DetailViewModelFactory(args, application)

      viewModel = ViewModelProvider(this,viewModelFactory)[DetailsViewModel::class.java]
        binding.data = viewModel
        return binding.root
    }


}