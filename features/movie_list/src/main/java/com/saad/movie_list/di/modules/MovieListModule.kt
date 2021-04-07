package com.saad.movie_list.di.modules

import com.saad.core.di.scopes.FeatureScope
import com.saad.core.repository.MovieListRepository
import com.saad.movie_list.MovieListViewModel
import dagger.Module
import dagger.Provides

@Module
class MovieListModule {
    @FeatureScope
    @Provides
    fun providesMovieListViewModel(
        repository: MovieListRepository
    ): MovieListViewModel = MovieListViewModel((repository))
}