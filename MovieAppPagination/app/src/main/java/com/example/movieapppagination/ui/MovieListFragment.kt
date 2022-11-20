package com.example.movieapppagination.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapppagination.api.MovieNetWork
import com.example.movieapppagination.databinding.FragmentMovieListBinding
import com.example.movieapppagination.pagination.MovieAdapter
import com.example.movieapppagination.viewmodel.MovieListViewModel
import com.example.movieapppagination.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel:MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMovieListBinding.inflate(inflater,container,false)
        binding.shimmerFramlayout.startShimmer()

        val factory = MovieViewModelFactory(MovieNetWork.getApi())
        viewModel = ViewModelProvider(this,factory)[MovieListViewModel::class.java]

        binding.lifecycleOwner = this



//        val swipHandler = object :SwipToDelete(context){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                super.onSwiped(viewHolder, direction)
//               val adapterRecycle = binding.recycleView.adapter as MovieAdapter
//            }
//
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieAdapter()
        binding.recycleView.adapter = adapter

        lifecycleScope.launch {
            viewModel.movieList.collect{
//                binding.shimmerFramlayout.visibility = View.GONE

//                binding.shimmerFramlayout.isVisible = false
                binding.recycleView.isVisible = true
//                if(it!=null) {
//                    binding.shimmerFramlayout.stopShimmer()
//                    binding.shimmerFramlayout.isVisible = false
//                    binding.recycleView.visibility = View.VISIBLE
                adapter.submitData(it)
//                }


            }
        }



        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return  false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }

        }).attachToRecyclerView(binding.recycleView)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}