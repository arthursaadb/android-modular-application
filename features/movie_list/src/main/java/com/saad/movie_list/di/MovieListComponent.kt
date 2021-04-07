package com.saad.movie_list.di

import com.saad.core.di.CoreComponent
import com.saad.core.di.scopes.FeatureScope
import com.saad.movie_list.MovieListFragment
import com.saad.movie_list.di.modules.MovieListModule
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [MovieListModule::class]
)
interface MovieListComponent {
    fun inject(movieListFragment: MovieListFragment)
}