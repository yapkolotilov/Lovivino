package com.ocean.lovivino.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LogInViewModel(
    private val logInService: LogInService,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun logIn(username: String, password: String): Single<String> {
        return logInService.logIn(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(username: String, password: String): Completable {
        return logInService.register(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveApiKey(apiKey: String) {
        sharedPreferences.edit().putString("api_key", apiKey).apply()
    }
}