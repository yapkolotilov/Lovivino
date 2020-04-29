package com.ocean.lovivino.login

import com.ocean.lovivino.utils.emulateDelay
import com.ocean.lovivino.web.LoggedOutApi
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LogInService @Inject constructor(
    var loggedOutApi: LoggedOutApi
) {
    private val users = mutableMapOf("admin" to "admin")

    fun logIn(username: String, password: String): Single<String> {
//        return loggedOutApi.logIn(username, password).map { it.body().apiKey }
        return Single.fromCallable {
            emulateDelay()
            if (!users.any { it.key == username && it.value == password })
                throw IllegalArgumentException("Invalid credentials. Try admin, admin")
            "bruh"
        }
    }

    fun register(username: String, password: String): Completable {
//        return loggedOutApi.register(username, password)
        return Completable.fromRunnable {
            emulateDelay()
            users[username] = password
        }
    }
}