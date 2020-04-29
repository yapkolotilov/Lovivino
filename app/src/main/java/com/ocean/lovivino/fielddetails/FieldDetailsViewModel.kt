package com.ocean.lovivino.fielddetails

import androidx.lifecycle.ViewModel
import com.ocean.lovivino.field.FieldService
import com.ocean.lovivino.web.FieldDto
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FieldDetailsViewModel(private val fieldService: FieldService) : ViewModel() {

    fun fieldAt(id: Long): Single<FieldDto> {
        return fieldService.getField(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}