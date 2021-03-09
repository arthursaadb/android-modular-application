package com.saad.core.repository

import androidx.paging.PagingData
import com.saad.core.network.responses.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    fun getMovies(page: Int): Flow<PagingData<MovieResponse>>
}