package com.saad.movie_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saad.modularapplicationexample.MovieApplication
import com.saad.movie_list.adapter.MovieListAdapter
import com.saad.movie_list.databinding.MovieListFragmentBinding
import com.saad.movie_list.di.DaggerMovieListComponent
import javax.inject.Inject

class MovieListFragment : Fragment() {
    private var _binding: MovieListFragmentBinding? = null
    private val binding: MovieListFragmentBinding = _binding!!
    private lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDependencyInjection() {
        DaggerMovieListComponent
            .builder()
            .coreComponent(MovieApplication.coreComponent(this.requireContext()))
            .build()
            .inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}