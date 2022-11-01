package com.example.usertodolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.usertodolist.adapter.UserTodoAdapter
import com.example.usertodolist.databinding.FragmentDetailsBinding
import com.example.usertodolist.domine.getNotAvailableResponse
import com.example.usertodolist.viewModel.UserTodoListViewModel
import com.example.usertodolist.viewModel.UserTodoListViewModelFactory


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
private lateinit var  viewModel:UserTodoListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        val args :DetailsFragmentArgs by navArgs()
        val adapter = UserTodoAdapter()

       val factory = UserTodoListViewModelFactory(args.userId,requireActivity().application)
        viewModel = ViewModelProvider(this,factory)[UserTodoListViewModel::class.java]
//        viewModel.filterList.observe(viewLifecycleOwner, Observer {
//
//        })

        binding.detailRecycleView.adapter = adapter
        viewModel.toDolist.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity, args.toString(), Toast.LENGTH_SHORT).show()
            println("\n\n\n\n${it.ifEmpty { getNotAvailableResponse() }} ${args}\n\n\n")
            if(it.isNotEmpty()){
                adapter.submitList(it)
            }else {
                adapter.submitList(listOf(getNotAvailableResponse()))
            }
        })

        return binding.root
    }

}