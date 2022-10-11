package com.example.hotelwithoutrecycleview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotelwithoutrecycleview.R
import com.example.hotelwithoutrecycleview.data.MenuDataBase
import com.example.hotelwithoutrecycleview.databinding.FragmentAddBinding
import com.example.hotelwithoutrecycleview.viewModel.AddViemModel
import com.example.hotelwithoutrecycleview.viewModel.addViewModelFactory


class AddFragment : Fragment() {
    lateinit var  binding: FragmentAddBinding
    lateinit var viewModel:AddViemModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)

        binding.addbtn.setOnClickListener {
            viewModel.add()
            findNavController().navigate(R.id.action_addFragment_to_displayFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = MenuDataBase.getInstance(application).dataBaseDao
        val factory = addViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this,factory)[AddViemModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val application = requireNotNull(this.activity).application
//        val dataSource = MenuDataBase.getInstance(application).dataBaseDao
//        val factory = addViewModelFactory(dataSource)
//        viewModel = ViewModelProvider(this,factory)[AddViemModel::class.java]
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this

    }

}
//val application = requireNotNull(activity).application
//val dataSource = ContactDatabase.getInstance(application).dataBaseDao
//val factory = HomeViewModelFactory(dataSource,application)
//viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
//binding.lifecycleOwner = this
//binding.viewModel = viewModel