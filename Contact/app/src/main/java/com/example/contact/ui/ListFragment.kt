package com.example.contact.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.adapter.ContactAdapter
import com.example.contact.databinding.FragmentListBinding
import com.example.contact.util.SwipeFeature
import com.example.contact.viewmodel.ContactViewModel
import com.example.contact.viewmodel.ContactViewmodelFactory


class listFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ContactViewModel
    private var clicked = false
    private lateinit var adapter: ContactAdapter



    private val rotateOpen:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_open_anim) }
    private val rotateClose:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_close_anim) }
    private val fromBottom :Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.from_bottom_anim) }
    private val toBottom:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.to_bottom_anim) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        val factory = ContactViewmodelFactory(application)
        viewModel = ViewModelProvider(this, factory)[ContactViewModel::class.java]
         adapter = ContactAdapter()
        binding.recyclerView.adapter = adapter

        searchContact()


        ItemTouchHelper(object :SwipeFeature(application){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val contact = adapter.currentList[position]
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        Toast.makeText(activity, "Edited", Toast.LENGTH_SHORT).show()
                    }
                    ItemTouchHelper.LEFT -> {
                        viewModel.deletteSingleContact(contact)
                        Toast.makeText(activity, "deleted  ${viewHolder.itemView.id.toString()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }).attachToRecyclerView(binding.recyclerView)

        viewModel.readAllContact.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
        })
        binding.floatingActionButton.setOnClickListener {
            addButtonClick()
        }
        binding.adding.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_deatilFragment)

        }

        binding.deleteAll.setOnClickListener {
            viewModel.deleteAllContact()
            Toast.makeText(activity, "All Contact Deleted", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun addButtonClick() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked:Boolean) {
        if(!clicked){
            binding.deleteAll.visibility = View.VISIBLE
            binding.adding.visibility = View.VISIBLE
        }else{
            binding.deleteAll.visibility = View.GONE
            binding.adding.visibility = View.GONE
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.deleteAll.startAnimation(fromBottom)
            binding.adding.startAnimation(fromBottom)
            binding.floatingActionButton.startAnimation(rotateOpen)
        }else{
            binding.deleteAll.startAnimation(toBottom)
            binding.adding.startAnimation(toBottom)
            binding.floatingActionButton.startAnimation(rotateClose)
        }
    }

    private fun searchContact(){
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    search(newText)
                }
                return true
            }

        })
    }


    private fun search(search:String){
        val query ="%$search%"
        val list = viewModel.searchContactList(query)
        list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }
}