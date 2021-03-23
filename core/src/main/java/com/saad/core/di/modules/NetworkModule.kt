package com.saad.core.di.modules

import com.saad.core.BuildConfig
import com.saad.core.network.interceptors.ApiKeyInterceptor
import com.saad.core.network.services.TmdbService
import com.saad.core.paging.MoviePagingSource
import com.saad.core.repository.MovieListRepository
import com.saad.core.repository.MovieListRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesApiKeyInterceptor(): ApiKeyInterceptor =
        ApiKeyInterceptor()

    @Singleton
    @Provides
    fun providesHttpHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): TmdbService = retrofit.create(TmdbService::class.java)
}