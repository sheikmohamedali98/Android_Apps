package com.example.hotel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotel.R
import com.example.hotel.data.MenuDatabase
import com.example.hotel.databinding.FragmentMenuAddBinding
import com.example.hotel.viewmodel.AddMenuViewModel
import com.example.hotel.viewmodel.AddViewModelFactory
import com.example.hotel.viewmodel.MenuViewModel
import com.example.hotel.viewmodel.MenuViewModelFactory


class MenuAddFragment : Fragment() {

    lateinit var binding: FragmentMenuAddBinding
    lateinit var viewModel: AddMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuAddBinding.inflate(inflater, container, false)

//        val application = requireNotNull(this.activity).application
//        val dataSource = MenuDatabase.getDatabase(application).menuDao()
//        val factory = AddViewModelFactory(dataSource,application)


//        viewModel = ViewModelProvider(application,factory)[AddMenuViewModel::class.java]
//        binding.viewModel = viewModel
//        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application
        val dataSource = MenuDatabase.getDatabase(application).menuDao()
        val factory = AddViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,factory)[AddMenuViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.addbtn.setOnClickListener {
            viewModel.add()
            findNavController().navigate(MenuAddFragmentDirections.actionMenuAddFragmentToMenuFragment())
        }
        return binding.root
    }

}