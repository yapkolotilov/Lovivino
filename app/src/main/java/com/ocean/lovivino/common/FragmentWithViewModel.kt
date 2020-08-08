package com.ocean.lovivino.common

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class FragmentWithViewModel<V : ViewModel, F : ViewModelProvider.Factory> : DaggerFragment() {

    @Inject lateinit var factory: F
    protected lateinit var viewModel: V
    protected abstract val classType: KClass<V>

    private var disposable = CompositeDisposable()

    @Inject lateinit var sharedPreferences: SharedPreferences

    protected var apiKey get() = sharedPreferences.getString("api_key", "")
        set(value) {
            sharedPreferences.edit().putString("api_key", value).apply()
        }

    override fun onStart() {
        super.onStart()
        disposable = CompositeDisposable()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, factory).get(classType.java)
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }

    protected fun Disposable.autoDispose() {
        disposable.add(this)
    }

    protected fun toastShortShow(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
}