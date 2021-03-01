package com.saad.core.network.responses

data class PopularMovieResponse(
    val page: Int,
    val movieResponses: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)