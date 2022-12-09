package com.example.contact.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contact.R
import com.example.contact.adapter.ContactAdapter
import com.example.contact.database.Contact
import com.example.contact.databinding.FragmentDeatilBinding
import com.example.contact.databinding.FragmentFavoriteContactBinding
import com.example.contact.viewmodel.FavoriteContactViewmodel
import com.example.contact.viewmodel.FavoriteContactViewmodelFactory


class FavoriteContactFragment : Fragment(), ContactAdapter.ClickListener {

    private lateinit var binding: FragmentFavoriteContactBinding
    private lateinit var viewmodel: FavoriteContactViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteContactBinding.inflate(inflater, container, false)

        val factory = FavoriteContactViewmodelFactory(requireActivity().application)
        viewmodel = ViewModelProvider(this, factory)[FavoriteContactViewmodel::class.java]
        val adapter = ContactAdapter(this@FavoriteContactFragment)

        binding.recyclerView.adapter = adapter

        viewmodel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }

    override fun onCardClickListener(contact: Contact) {
        findNavController().navigate(FavoriteContactFragmentDirections.actionFavoriteContactFragmentToContactDetailFragment(
            contact.id))
    }

}