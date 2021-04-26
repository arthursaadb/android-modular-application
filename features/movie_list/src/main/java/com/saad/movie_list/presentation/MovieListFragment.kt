package com.saad.movie_list.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.saad.modularapplicationexample.MovieApplication
import com.saad.movie_list.databinding.MovieListFragmentBinding
import com.saad.movie_list.di.DaggerMovieListComponent
import com.saad.movie_list.di.modules.MovieListModule
import com.saad.movie_list.presentation.loadstate.LoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : Fragment() {
    private var binding: MovieListFragmentBinding? = null
    private var searchJob: Job? = null
    private lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDependencyInjection()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        search()
    }

    private fun initAdapter() {
        adapter = MovieListAdapter()

        binding?.movieList?.adapter = adapter

        binding?.movieList?.adapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding?.movieList?.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding?.progress?.isVisible = loadState.source.refresh is LoadState.Loading
            binding?.retryButton?.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun showEmptyList(listEmpty: Boolean) {

    }

    private fun initDependencyInjection() {
        DaggerMovieListComponent
            .builder()
            .coreComponent(MovieApplication.coreComponent(this.requireContext()))
            .movieListModule(MovieListModule())
            .build()
            .inject(this)
    }

    private fun search() {
        searchJob?.cancel()

        searchJob = lifecycleScope.launch {
            viewModel.getMovieList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}