package com.saad.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.saad.core.network.responses.MovieResponse
import com.saad.core.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepositoryImpl(private val moviePagingSource: MoviePagingSource) :
    MovieListRepository {

    override fun getMovies(page: Int): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { moviePagingSource }
        ).flow
}