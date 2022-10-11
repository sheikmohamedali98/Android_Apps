package com.example.hotelwithoutrecycleview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelwithoutrecycleview.R
import com.example.hotelwithoutrecycleview.data.MenuDataBase
import com.example.hotelwithoutrecycleview.databinding.FragmentDisplayBinding
import com.example.hotelwithoutrecycleview.viewModel.DisplayViewModel
import com.example.hotelwithoutrecycleview.viewModel.DisplayViewModelFactory


class DisplayFragment : Fragment() {
    lateinit var  binding: FragmentDisplayBinding
    lateinit var viewModel: DisplayViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_display,container,false)
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_displayFragment_to_addFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(activity).application
        val dataSource = MenuDataBase.getInstance(application).dataBaseDao
        val factory = DisplayViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(this,factory)[DisplayViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

}