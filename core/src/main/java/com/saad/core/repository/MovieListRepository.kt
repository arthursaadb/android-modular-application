package com.saad.core.repository

import com.saad.core.network.Resource
import com.saad.core.network.responses.PopularMovieResponse

interface MovieListRepository {
    suspend fun getMovies(page: Int): Resource<PopularMovieResponse>
}