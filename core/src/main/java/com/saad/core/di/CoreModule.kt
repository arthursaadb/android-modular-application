package com.saad.core.di

import com.saad.core.BuildConfig
import com.saad.core.network.interceptors.ApiKeyInterceptor
import com.saad.core.network.services.TmdbService
import com.saad.core.repository.MovieListRepository
import com.saad.core.repository.MovieListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class CoreModule {
    @Binds
    abstract fun bindsMovieListRepository(movieListRepositoryImpl: MovieListRepositoryImpl):
            MovieListRepository

    companion object {
        @Singleton
        @Provides
        fun providesApiKeyInterceptor() = ApiKeyInterceptor()

        @Singleton
        @Provides
        fun providesOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        @Singleton
        @Provides
        fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BuildConfig.TMDB_API_BASE_URL)
                .client(okHttpClient)
                .build()

        @Singleton
        @Provides
        fun providesTmdbService(retrofit: Retrofit): TmdbService =
            retrofit.create(TmdbService::class.java)
    }
}
