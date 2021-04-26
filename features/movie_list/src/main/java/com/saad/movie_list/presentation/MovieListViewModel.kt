package com.saad.movie_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.saad.core.network.responses.MovieResponse
import com.saad.core.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun getMovieList(): Flow<PagingData<MovieResponse>> =
        repository.getMovies().cachedIn(viewModelScope)

}