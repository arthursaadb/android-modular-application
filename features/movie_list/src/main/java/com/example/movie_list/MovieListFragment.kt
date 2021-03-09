package com.example.movie_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movie_list.adapter.MovieListAdapter
import com.example.movie_list.databinding.MovieListFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    fun getMovies(page: Int) {
        searchJob?.cancel()

        searchJob = lifecycleScope.launch {
            viewModel.getMovieList(page)?.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        fun newInstance() = MovieListFragment()
    }
}