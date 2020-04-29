package com.ocean.lovivino.di

import com.ocean.lovivino.MainActivity
import com.ocean.lovivino.field.FieldFragment
import com.ocean.lovivino.fielddetails.FieldDetailsFragment
import com.ocean.lovivino.login.LogInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun fieldFragmentInjector(): FieldFragment

    @ContributesAndroidInjector
    abstract fun logInFragmentInjector(): LogInFragment

    @ContributesAndroidInjector
    abstract fun fieldDetailsFragmentInjector(): FieldDetailsFragment
}