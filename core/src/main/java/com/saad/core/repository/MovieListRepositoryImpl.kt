package com.saad.core.repository

import com.saad.core.network.Resource
import com.saad.core.network.responses.PopularMovieResponse
import com.saad.core.network.services.TmdbService
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val service: TmdbService) :
    MovieListRepository {
    override suspend fun getMovies(page: Int): Resource<PopularMovieResponse> =
        service.getPopularMovies(page)
}