package com.ocean.lovivino.web

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("api_key")
    val apiKey: String
)