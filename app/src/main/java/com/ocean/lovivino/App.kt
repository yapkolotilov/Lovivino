package com.ocean.lovivino

import android.app.Application
import com.ocean.lovivino.di.DaggerAppComponent
import com.ocean.lovivino.di.DataSourcesModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .dataSourcesModule(DataSourcesModule(this))
            .create(this)
            .inject(this)

        Timber.plant(Timber.DebugTree())
    }

    override fun androidInjector(): AndroidInjector<Any> = injector
}