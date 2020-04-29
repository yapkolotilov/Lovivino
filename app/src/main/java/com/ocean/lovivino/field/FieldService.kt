package com.ocean.lovivino.field

import android.content.Context
import com.ocean.lovivino.R
import com.ocean.lovivino.utils.emulateDelay
import com.ocean.lovivino.web.FieldDto
import com.ocean.lovivino.web.LoggedInApi
import com.ocean.lovivino.web.SensorDto
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class FieldService @Inject constructor(
    val loggedInApi: LoggedInApi,
    val context: Context
) {
    fun getFieldIds(): Single<List<Long>> {
//        return loggedInApi.getFieldIds().map { it.response }
        return Single.fromCallable {
            emulateDelay()
            listOf<Long>(1, 2, 3, 4, 5)
        }
    }

    fun getField(id: Long): Single<FieldDto> {
//        return loggedInApi.getField(id).map { it.response }
        return Single.fromCallable {
//            emulateDelay()
            val okSensor = SensorDto(
                status = SensorDto.Status.OK,
                date = Date(),
                temperature30 = 15,
                temperature60 = 12,
                moisture30 = 20,
                moisture60 = 40
            )
            val weedSensor = SensorDto(
                status = SensorDto.Status.WEED_FOUND,
                date = Date(),
                temperature30 = 10,
                temperature60 = 8,
                moisture30 = 13,
                moisture60 = 30
            )
            val workingSensor = SensorDto(
                status = SensorDto.Status.WORKING,
                date = Date(),
                temperature30 = 10,
                temperature60 = 8,
                moisture30 = 13,
                moisture60 = 30
            )

            val sensors = MutableList(10) { okSensor }.apply {
                addAll(List(5) { weedSensor })
                addAll(List(10) { workingSensor })
            }

            return@fromCallable listOf(
                FieldDto(
                    id = 1,
                    name = "Field 1",
                    status = FieldDto.Status.OK,
                    description = context.getString(R.string.lores_ipsum_1),
                    sensors = sensors.shuffled()
                ),
                FieldDto(
                    id = 2,
                    name = "Field 2",
                    status = FieldDto.Status.OK,
                    description = context.getString(R.string.lores_ipsum_2),
                    sensors = sensors.shuffled()
                ),
                FieldDto(
                    id = 3,
                    name = "Field 3",
                    status = FieldDto.Status.OK,
                    description = context.getString(R.string.lores_ipsum_3),
                    sensors = sensors.shuffled()
                ),
                FieldDto(
                    id = 4,
                    name = "Field 4",
                    status = FieldDto.Status.OK,
                    description = context.getString(R.string.lores_ipsum_4),
                    sensors = sensors.shuffled()
                ),
                FieldDto(
                    id = 5,
                    name = "Field 5",
                    status = FieldDto.Status.OK,
                    description = context.getString(R.string.lores_ipsum_5),
                    sensors = sensors.shuffled()
                )
            ).first { it.id == id }
        }
    }
}