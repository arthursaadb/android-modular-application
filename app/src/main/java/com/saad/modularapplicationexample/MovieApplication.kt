package com.saad.modularapplicationexample

import android.app.Application
import android.content.Context
import com.saad.core.di.CoreComponent
import com.saad.core.di.DaggerCoreComponent
import com.saad.modularapplicationexample.di.DaggerAppComponent

class MovieApplication : Application() {
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    private fun initCoreDependencyInjection() {
        DaggerAppComponent.builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initAppDependencyInjection() {
        coreComponent = DaggerCoreComponent.builder().build()
    }

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as? MovieApplication)?.coreComponent
    }
}