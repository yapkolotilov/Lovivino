package com.ocean.lovivino.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class LogInViewModelFactory @Inject constructor(
    var logInService: LogInService,
    var sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogInViewModel(logInService, sharedPreferences) as T
    }
}