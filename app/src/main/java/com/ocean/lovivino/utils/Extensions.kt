package com.ocean.lovivino.utils

import android.content.SharedPreferences
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit

inline fun <reified T : ViewModel> ViewModelProvider.get(): T {
    return get(T::class.java)
}

inline fun <reified T> Retrofit.create(): T {
    return create(T::class.java)
}

fun TextView.textString(): String = text.toString()

fun Disposable.autoDispose() {
    compositeDisposable.add(this)
}

var SharedPreferences.apiKey get() = getString("api_key", "")
    set(value) { edit().putString("api_key", value).apply() }

fun emulateDelay() {
    Thread.sleep(500L)
}

private val compositeDisposable by lazy { CompositeDisposable() }