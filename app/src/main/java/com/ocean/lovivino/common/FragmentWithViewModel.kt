package com.ocean.lovivino.common

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class FragmentWithViewModel<V : ViewModel, F : ViewModelProvider.Factory> : DaggerFragment() {

    @Inject lateinit var factory: F
    protected lateinit var viewModel: V
    protected abstract val classType: KClass<V>

    @Inject lateinit var sharedPreferences: SharedPreferences

    protected var apiKey get() = sharedPreferences.getString("api_key", "")
        set(value) {
            sharedPreferences.edit().putString("api_key", value).apply()
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, factory).get(classType.java)
    }

    protected fun toastShortShow(message: String?) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }
}