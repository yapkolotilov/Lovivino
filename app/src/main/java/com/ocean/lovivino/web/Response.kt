package com.ocean.lovivino.web

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("response")
    val response: T
)