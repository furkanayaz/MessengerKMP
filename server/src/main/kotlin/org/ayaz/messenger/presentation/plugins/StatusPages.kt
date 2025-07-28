package org.ayaz.messenger.presentation.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.SerializationException
import org.ayaz.messenger.data.util.Response

fun Application.installStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when (throwable) {
                is SerializationException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, description = "Serialization Exception"))
                is BadRequestException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, description = "Your entered field(s) is not null or empty."))
                else -> call.respond(HttpStatusCode.InternalServerError, Response.Error(errorCode = 500, description = "Occurred an unknown exception in server."))
            }
        }
    }
}