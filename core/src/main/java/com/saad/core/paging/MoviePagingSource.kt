package com.saad.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saad.core.network.responses.MovieResponse
import com.saad.core.network.services.TmdbService
import java.lang.Exception

private const val MOVIE_PAGING_START_INDEX = 1

class MoviePagingSource(
    private val service: TmdbService,
) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: MOVIE_PAGING_START_INDEX
        val response = service.getPopularMovies(position)
        val movies = response.data?.movieResponses

        return try {
            val nextIndex = if (movies.isNullOrEmpty()) {
                null
            } else {
                params.key?.inc()
            }

            LoadResult.Page(
                data = movies ?: listOf(),
                prevKey = if (position == MOVIE_PAGING_START_INDEX) null else position.minus(1),
                nextKey = nextIndex
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}