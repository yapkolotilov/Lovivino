package com.ocean.lovivino.web

import com.google.gson.annotations.SerializedName
import java.util.*

data class SensorDto(
    @SerializedName("status")
    val status: Status,
    @SerializedName("date")
    val date: Date,
    @SerializedName("temperature_30")
    val temperature30: Int,
    @SerializedName("temperature_60")
    val temperature60: Int,
    @SerializedName("moisture_30")
    val moisture30: Int,
    @SerializedName("moisture_60")
    val moisture60: Int
) {

    enum class Status {
        @SerializedName("Ok") OK,
        @SerializedName("Weed_Found") WEED_FOUND,
        @SerializedName("Working") WORKING
    }
}