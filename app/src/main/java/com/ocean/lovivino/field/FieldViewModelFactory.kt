package com.ocean.lovivino.field

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FieldViewModelFactory @Inject constructor(
    val fieldService: FieldService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FieldViewModel(fieldService) as T
    }
}
