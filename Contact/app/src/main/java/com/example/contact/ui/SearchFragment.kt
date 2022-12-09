package com.example.contact.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.contact.R
import com.example.contact.adapter.ContactAdapter
import com.example.contact.database.Contact
import com.example.contact.databinding.FragmentSearchBinding
import com.example.contact.viewmodel.ContactViewModel
import com.example.contact.viewmodel.ContactViewmodelFactory
import com.example.contact.viewmodel.SearchViewmodel
import com.example.contact.viewmodel.SearchViewmodelFactory


class SearchFragment : Fragment(), ContactAdapter.ClickListener {


    private lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel: SearchViewmodel
    private lateinit var adapter: ContactAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        val factory = SearchViewmodelFactory(application)
        viewModel = ViewModelProvider(this, factory)[SearchViewmodel::class.java]
        adapter = ContactAdapter(this@SearchFragment)
        binding.recyclerView.adapter = adapter
//        adapter.setOnItemClickListener(object :ContactAdapter.OnItemClickListener{
//            override fun onItemClick(position: Int) {
//                findNavController().navigate(R.id.action_searchFragment_to_contactDetailFragment2)
//            }
//
//        })

            searchContact()


        return binding.root
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
                        if(newText.isEmpty()){
                            binding.recyclerView.isVisible = false
                            binding.result.isVisible = true
                            return true
                        }
                        search(newText)
                    }
                    return true
                }

            })
    }


    private fun search(search:String){
        val query ="%$search%"
        val list = viewModel.searchContactList(query)
        list.observe(viewLifecycleOwner, Observer {list->
            if(list!=null) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.result.visibility = View.INVISIBLE
                adapter.submitList(list)
            }else{

                binding.recyclerView.visibility  = View.INVISIBLE
                binding.result.visibility = View.VISIBLE
            }
        })

    }

    override fun onCardClickListener(contact: Contact) {
//        Toast.makeText(requireContext(), "Success ${contact.id}", Toast.LENGTH_SHORT).show()
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToContactDetailFragment2(contact.id))
    }
}