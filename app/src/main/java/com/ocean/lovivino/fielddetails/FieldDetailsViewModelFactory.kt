package com.ocean.lovivino.fielddetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ocean.lovivino.field.FieldService
import javax.inject.Inject

class FieldDetailsViewModelFactory @Inject constructor(
    val fieldService: FieldService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FieldDetailsViewModel(fieldService) as T
    }
}