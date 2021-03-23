package com.saad.modularapplicationexample.di.module

import android.content.Context
import com.saad.modularapplicationexample.MovieApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesContext(application: MovieApplication): Context = application.applicationContext
}