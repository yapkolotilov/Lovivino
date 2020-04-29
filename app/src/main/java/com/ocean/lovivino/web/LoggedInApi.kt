package com.ocean.lovivino.web

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LoggedInApi {

    @GET("/fields/ids")
    fun getFieldIds(): Single<Response<List<Long>>>

    @GET("fields/{id}")
    fun getField(@Path("id") id: Long): Single<Response<FieldDto>>
}