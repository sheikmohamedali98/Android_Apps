package com.example.usertodolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.usertodolist.R
import com.example.usertodolist.adapter.UserAdapter
import com.example.usertodolist.databinding.FragmentUserBinding
import com.example.usertodolist.viewModel.UserViewModel
import com.example.usertodolist.viewModel.UserViewModelFactory


class UserFragment : Fragment() {

    private lateinit var  binding: FragmentUserBinding
    private lateinit var  viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater,container,false)

        val factory = UserViewModelFactory(requireActivity().application)
        val adapter = UserAdapter(UserAdapter.OnClickListner{
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToDetailsFragment(it))
            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()

        })
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]
        binding.recycleView.adapter = adapter
        binding.lifecycleOwner = this

        viewModel.listdata.observe(viewLifecycleOwner, Observer {

                adapter.submitList(it)
        })

        return binding.root
    }


}