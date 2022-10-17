package com.example.thirukural.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.thirukural.R
import com.example.thirukural.databinding.FragmentHomeBinding
import com.example.thirukural.model.Thirukural
import com.example.thirukural.repository.Repository
import com.example.thirukural.viewmodel.HomeViewModel
import com.example.thirukural.viewmodel.HomeViewModelFactory


class HomeFragment : Fragment() {
lateinit var  binding: FragmentHomeBinding
lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)


        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]

        val list = mutableListOf <String>()

        binding.getButton.setOnClickListener {
            val myNumber = binding.number.text.toString()
//            val end = myNumber.toString().toInt()*10;
//            val start = end-9;
//            for(number in start ..end ) {

                viewModel.getKurals(myNumber.toInt())
//            }
//            viewModel.myKural.observe(viewLifecycleOwner, Observer {response->
//
//                if(response.isSuccessful){
//                    list.add(response.body().toString())
//                }else{
//                    binding.display.text = response.code().toString()
//                }
//            })
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}