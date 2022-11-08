package com.example.agecalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.agecalculator.databinding.FragmentAgeBinding


class AgeFragment : Fragment() {


private lateinit var binding: FragmentAgeBinding
private lateinit var viewModel: AgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgeBinding.inflate(inflater,container,false)
        val factory = AgeViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this,factory)[AgeViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        binding.btnDatePicker.setOnClickListener {
//            viewModel.clickPicker()
//        }
//        viewModel.selectDate.observe(viewLifecycleOwner, Observer {
//            binding.tvSelectedDate.text = it
//
//        })
//
//        viewModel.minutes.observe(viewLifecycleOwner, Observer {
//            binding.tvSelectedDateInMinutes.text = it
//
//        })


        return binding.root
    }

}