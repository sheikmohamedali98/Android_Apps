package com.example.findcolor.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.findcolor.R
import com.example.findcolor.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //binding = DataBindingUtil.inflate(inflater,R.id.title_layout,container,false)
        //corrected here
        binding = FragmentTitleBinding.inflate(inflater)
        // Inflate the layout for this fragment
        binding.playGameButton.setOnClickListener {
            //corrected here
            findNavController().navigate(TitleFragmentDirections.actionTitleDestinationToGameDestination())
        }
        return binding.root
    }


}