package org.ayaz.messenger.presentation.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import jakarta.validation.ConstraintViolationException
import kotlinx.serialization.SerializationException
import org.ayaz.messenger.data.util.Response
import org.ayaz.messenger.presentation.util.MessengerExceptions

fun Application.installStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, throwable ->
            when (throwable) {
                is MessengerExceptions.MissingBodyException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, errorMessages = listOf(throwable.message)))
                is MessengerExceptions.MissingFieldException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, errorMessages = listOf(throwable.message)))
                is SerializationException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, errorMessages = listOf("Occurred a serialization error.")))
                is BadRequestException -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorCode = 400, errorMessages = listOf(throwable.message ?: "Your entered field(s) cannot null or empty.")))
                is ConstraintViolationException -> {
                    val fieldErrors = throwable.constraintViolations.map { it.propertyPath }.distinct().sortedBy { it.toString().first() }.joinToString()
                    val errorMessage = "Your entered $fieldErrors field(s) cannot be valid. Please review your information."
                    call.respond(HttpStatusCode.BadRequest, Response.Error(errorMessages = listOf(errorMessage)))
                }
                else -> call.respond(HttpStatusCode.InternalServerError, Response.Error(errorCode = 500, errorMessages = listOf("Occurred an unknown exception in server.")))
            }
        }
    }
}