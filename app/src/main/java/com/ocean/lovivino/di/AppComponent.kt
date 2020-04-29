package com.ocean.lovivino.di

import com.ocean.lovivino.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DataSourcesModule::class,
    AbstractDataSourcesModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Suppress("DEPRECATION")
    @Component.Builder
    abstract class Factory: AndroidInjector.Builder<App>() {

        abstract fun dataSourcesModule(dataSourcesModule: DataSourcesModule): Factory
    }
}