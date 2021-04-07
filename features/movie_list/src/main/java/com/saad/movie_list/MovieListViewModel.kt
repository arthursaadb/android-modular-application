package com.saad.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saad.core.network.responses.MovieResponse
import com.saad.core.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: MovieListRepository) : ViewModel() {
    private var currentPage = 0
    private var currentMovieResult: Flow<PagingData<MovieResponse>>? = null

    fun getMovieList(page: Int): Flow<PagingData<MovieResponse>> {
        val lastResult = currentMovieResult

        if (page == currentPage && lastResult != null) {
            return lastResult
        }

        currentPage = page
        val newResult = repository.getMovies(currentPage).cachedIn(viewModelScope)
        currentMovieResult = newResult

        return newResult
    }
}