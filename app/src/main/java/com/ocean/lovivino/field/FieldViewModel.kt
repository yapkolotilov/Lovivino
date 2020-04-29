package com.ocean.lovivino.field

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FieldViewModel(
    private val fieldService: FieldService
) : ViewModel() {

    val fieldIds: Single<List<Long>> get() = fieldService.getFieldIds()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}