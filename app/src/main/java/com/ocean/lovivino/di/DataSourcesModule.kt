package com.ocean.lovivino.di

import android.content.Context
import android.content.SharedPreferences
import com.ocean.lovivino.App
import com.ocean.lovivino.utils.apiKey
import com.ocean.lovivino.utils.create
import com.ocean.lovivino.web.LoggedInApi
import com.ocean.lovivino.web.LoggedOutApi
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataSourcesModule(private val app: App) {

    @Provides
    @Singleton
    fun getSharedPreferences(): SharedPreferences = app.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getApplicationContext(): Context = app

    @Provides
    @Singleton
    fun getLoggedOutApi(): LoggedOutApi = buildRetrofit(BASE_URL).build().create()

    @Provides
    @Singleton
    fun getLoggedInApi(sharedPreferences: SharedPreferences): LoggedInApi {
        return buildRetrofit(BASE_URL)
            .client(buildOkHttpClient(sharedPreferences))
            .build()
            .create()
    }

    private fun buildOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic(sharedPreferences.apiKey, ""))
                    .build()
                return chain.proceed(request)
            }
        }).build()
    }

    @Suppress("SameParameterValue")
    private fun buildRetrofit(url: String): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    private companion object {
        const val BASE_URL = "http://192.168.1.33"
    }
}