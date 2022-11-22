package com.example.movieapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.networkconnectivity.ConnectionObserver
import com.example.movieapp.networkconnectivity.NetworkConnectionObserver
import com.example.movieapp.viewModel.MovieListViewModel
import com.example.movieapp.viewModel.MovieViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var connectivityObserver:ConnectionObserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val application = requireNotNull(requireActivity().application)
        val factory = MovieViewModelFactory(application)
        connectivityObserver = NetworkConnectionObserver(application)
        viewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        adapter = MovieAdapter(MovieAdapter.OnClickListener {
            viewModel.displayData(it)
        })
        binding.recycleview.adapter = adapter
        //bottom navigate
        viewModel.listOfMovie.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.pogress.isVisible = false
                adapter.submitList(it)
            }
        })



        viewModel.navigateToSelectedMovies.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it))
                viewModel.displayProperties()
            }
        })



        connectivityObserver.observer().onEach {
            when(it.toString()){
                "Available"->{
                        Toast.makeText(activity, "Available", Toast.LENGTH_SHORT).show()
                    binding.animation.isVisible = false
                    binding.pogress.isVisible = false
                        binding.recycleview.isVisible = true
                        viewModel.getMovieFromRepository()

                }
                "Unavailable"->{

                }
                "Losing"->{

                }
                "Lost" -> {
                    Toast.makeText(activity, "Lost", Toast.LENGTH_SHORT).show()
                    binding.pogress.isVisible = true
                    binding.animation.isVisible = true
                    binding.recycleview.isVisible = false
                    binding.animation.setAnimation(R.raw.lost_connection)
                    binding.animation.playAnimation()
                }
            }
        }.launchIn(lifecycleScope)


        setupMenu()
        return binding.root
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {

            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                (menu.findItem(R.id.app_bar_search).actionView as SearchView).setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            if (query != null) {
                                searchString(query)
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            if (newText != null) {
                                searchString(newText)
                            }
                            return true
                        }
                    })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun searchString(string: String) {
        val query = "%$string%"
        val list = viewModel.searchQuery(query)
        list.observe(this, Observer {
            adapter.submitList(it)
        })

    }

}