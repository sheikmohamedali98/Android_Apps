package com.example.hotel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hotel.R
import com.example.hotel.data.MenuDatabase
import com.example.hotel.databinding.FragmentMenuBinding
import com.example.hotel.viewmodel.MenuViewModel
import com.example.hotel.viewmodel.MenuViewModelFactory

class MenuFragment : Fragment() {
    lateinit var binding:com.example.hotel.databinding.FragmentMenuBinding
    lateinit var  viewModel: MenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false)
//        binding.floatingActionButton1.setOnClickListener{
//            view?.findNavController()?.navigate(R.id.action_menuFragment_to_menuAddFragment)
//        }

//        val application = requireNotNull(this.activity).application
//        val dataSource = MenuDatabase.getDatabase(application).menuDao()
//        val factory = MenuViewModelFactory(dataSource,application)

//        viewModel= ViewModelProvider(this,factory)[MenuViewModel::class.java]
//        binding.setLifecycleOwner(this)
//        binding.viewModel = viewModel

        val application = requireNotNull(this.activity).application
        val dataSource = MenuDatabase.getDatabase(application).menuDao()
        val factory = MenuViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,factory)[MenuViewModel::class.java]

        val adapter = MenuListAdapter()
        binding.recycleView.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })




//        //set adapter
//        val adapter = ContactAdapter()
//        //setObservers
//        viewModel.contacts.observe(viewLifecycleOwner, Observer {
//            adapter.data = it
//        })
//        setOnclickListener()
//        //recycler view
//        binding.recyclerView.adapter = adapter

//        val menuAdapter = MenuListAdapter(this)
//        menuAdapter.menuList =listOf(Menu(1,"fsd",12.0))
//        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.layoutManager = linearLayoutManager
//        binding.recycleView.adapter = menuAdapter
//        viewModel.menus.observe(viewLifecycleOwner, Observer {
//            menuAdapter.menuList = it
//        })

//error
//        adapter.data= viewModel.menus.value?: listOf<Menu>()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToMenuAddFragment())
        }
        return binding.root
    }


}