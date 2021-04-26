package com.saad.core.di.modules

import com.saad.core.BuildConfig
import com.saad.core.network.interceptors.ApiKeyInterceptor
import com.saad.core.network.services.TmdbService
import com.saad.core.paging.MoviePagingSource
import com.saad.core.repository.MovieListRepository
import com.saad.core.repository.MovieListRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object CoreModule {
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
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Singleton
    @Provides
    fun providesMoviePagingSource(tmdbService: TmdbService): MoviePagingSource =
        MoviePagingSource(tmdbService)

    @Singleton
    @Provides
    fun providesMovieListRepository(moviePagingSource: MoviePagingSource): MovieListRepository =
        MovieListRepositoryImpl(moviePagingSource)

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): TmdbService = retrofit.create(TmdbService::class.java)


}