package org.ayaz.messenger.presentation.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.SerializationException
import org.ayaz.messenger.data.util.Response

fun Application.installStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when (throwable) {
                is SerializationException -> {
                    call.respond(Response.Error(errorCode = 400, description = "Serialization Exception"))
                }

                is IllegalArgumentException -> {
                    call.respond(Response.Error(errorCode = 400, description = throwable.message.orEmpty()))
                }

                else -> {
                    call.respond(Response.Error(errorCode = 500, description = "Occurred an unknown exception in server."))
                }
            }
        }
    }
}