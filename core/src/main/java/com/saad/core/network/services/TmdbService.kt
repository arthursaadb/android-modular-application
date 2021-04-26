package com.saad.core.network.services

import com.saad.core.network.Resource
import com.saad.core.network.responses.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): PopularMovieResponse
}