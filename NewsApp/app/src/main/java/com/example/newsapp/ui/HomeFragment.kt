package com.example.newsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.adapter.VideoAdapter
import com.example.newsapp.api.NewsFilter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

//    lateinit var binding:Bin
    private  lateinit var  binding: FragmentHomeBinding
    private  lateinit var  viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recycleView.adapter = VideoAdapter()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.over_flow_menu,menu)
    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.sport->viewModel.getNewsProperties("sports")
//
//        }
////            viewModel.updateFilter(  when(item.itemId){
////                R.id.sport->viewModel.getNewsProperties("sports")
////                R.id.business->NewsFilter.BUSINESS
////                R.id.autoMobile->NewsFilter.AUTOMOBILE
////                R.id.technology->NewsFilter.TECHNOLOGY
////                else -> {NewsFilter.ALL}
////            })
//                return super.onOptionsItemSelected(item);
//        }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        R.id.sport-> Toast.makeText(activity, "Sport Clicked", Toast.LENGTH_SHORT).show()
        R.id.allNews->Toast.makeText(activity, "All News Clicked", Toast.LENGTH_SHORT).show()
        R.id.technology->Toast.makeText(activity, "Technology Clicked", Toast.LENGTH_SHORT).show()
    }
        return super.onOptionsItemSelected(item)
    }
    }


