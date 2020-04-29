package com.ocean.lovivino.web

import com.google.gson.annotations.SerializedName

data class FieldDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Status,
    @SerializedName("description")
    val description: String,
    @SerializedName("sensors")
    val sensors: List<SensorDto>
) {

    enum class Status {
        @SerializedName("Ok")
        OK {
            override fun toString(): String {
                return "OK"
            }
        },
        @SerializedName("Found_Weed")
        FOUND_WEED {
            override fun toString(): String {
                return "Найден сорняк"
            }
        },
        @SerializedName("Working")
        WORKING {
            override fun toString(): String {
                return "Ведутся работы"
            }
        }
    }
}