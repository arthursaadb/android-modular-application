package com.saad.core.di

import com.saad.core.di.modules.NetworkModule
import com.saad.core.network.services.TmdbService
import com.saad.core.repository.MovieListRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreComponent {
    fun tmdbService(): TmdbService
}