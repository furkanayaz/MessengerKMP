package org.ayaz.messenger.presentation.util

import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receiveNullable
import io.ktor.server.routing.RoutingCall
import org.ayaz.messenger.data.util.jwt.JWTValues
import org.ayaz.messenger.presentation.util.validations.Validator
import org.koin.ktor.ext.inject

object CallUtil {
    fun ApplicationEnvironment.getJWTValues(): JWTValues {
        val secretKey = config.propertyOrNull(JWTValues.SECRET_KEY)?.getString().orEmpty()
        val issuer = config.propertyOrNull(JWTValues.ISSUER)?.getString().orEmpty()
        val audience = config.propertyOrNull(JWTValues.AUDIENCE)?.getString().orEmpty()

        return JWTValues(secretKey, issuer, audience)
    }

    suspend inline fun <reified T> RoutingCall.require(): T {
        val validator: Validator by inject()
        val body = try {
            this.receiveNullable<T>() ?: throw MessengerExceptions.MissingBodyException()
        } catch (_: BadRequestException) {
            throw MessengerExceptions.MissingFieldException()
        }

        validator.validate(body)

        return body
    }
}