package com.example.newsapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapter.VideoAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.domain.DomainData
import com.example.newsapp.viewmodel.HomeViewModel
import com.example.newsapp.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.newSingleThreadContext


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

        val factory = HomeViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = VideoAdapter().also {
            it.setOnclickListener { news ->
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(news))
            }
        }
//        val list:List<>z = DomainData("Anmol Sharma","Talking about India-Pakistan T20 World Cup 2022 match which India won on last ball","25 Oct 2022,Tuesday","faa47543c0e84474a2443e39bb701d22","https://static.inshorts.com/inshorts/images/v1/variants/jpg/m/2022/10_oct/25_tue/img_1666671280427_494.jpg?","https://www.hindustantimes.com/cricket/just-stop-the-world-cup-there-australia-star-s-mitchell-marsh-epic-remark-after-drama-filled-mcg-clash-between-india-and-pakistan-101666604175869-amp.html?utm_campaign=fullarticle&utm_medium=referral&utm_source=inshorts","12121","Hello","qwertokjcvbnm,")
        binding.recycleView.adapter = adapter


        val lisSize = viewModel.listOfNews.value
        Toast.makeText(activity, "${lisSize}", Toast.LENGTH_LONG).show()

        viewModel.listOfNews.observe(viewLifecycleOwner){

             adapter.submitList(it)
                println("\n\n\n${adapter.submitList(it)}\n\n")
        }

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


