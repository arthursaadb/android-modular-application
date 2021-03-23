package com.saad.modularapplicationexample.di

import com.saad.core.di.CoreComponent
import com.saad.core.di.scopes.AppScope
import com.saad.modularapplicationexample.MovieApplication
import com.saad.modularapplicationexample.di.module.AppModule
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: MovieApplication)
}