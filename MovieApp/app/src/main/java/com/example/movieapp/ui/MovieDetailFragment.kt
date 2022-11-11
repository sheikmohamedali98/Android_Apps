package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.viewModel.MovieDetailViewModel
import com.example.movieapp.viewModel.MovieDetailViewmodelFactory


class MovieDetailFragment : Fragment() {

private lateinit var  binding: FragmentMovieDetailBinding
private lateinit var viewModel: MovieDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater,container,false)

        val args : MovieDetailFragmentArgs by navArgs()
        val factory = MovieDetailViewmodelFactory(args.movieSelect)
        viewModel = ViewModelProvider(this,factory)[MovieDetailViewModel::class.java]

        binding.viewModel = viewModel


        binding.backNavigate.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieListFragment())
        }
        return  binding.root
    }


}