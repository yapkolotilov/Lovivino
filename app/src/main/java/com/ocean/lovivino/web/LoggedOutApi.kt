package com.ocean.lovivino.web

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface LoggedOutApi {

    @POST("login")
    fun logIn(
        @Header("Login") username: String,
        @Header("Password") password: String
    ): Single<Response<LoginDto>>

    @POST("register")
    fun register(
        @Header("Login") username: String,
        @Header("Password") password: String
    ): Completable
}