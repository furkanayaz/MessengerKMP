package org.ayaz.messenger.presentation.util

import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receiveNullable
import io.ktor.server.routing.RoutingCall
import org.ayaz.messenger.data.util.jwt.JWTValues

object CallUtil {
    fun ApplicationEnvironment.getJWTValues(): JWTValues {
        val secretKey = config.propertyOrNull(JWTValues.SECRET_KEY)?.getString().orEmpty()
        val issuer = config.propertyOrNull(JWTValues.ISSUER)?.getString().orEmpty()
        val audience = config.propertyOrNull(JWTValues.AUDIENCE)?.getString().orEmpty()

        return JWTValues(secretKey, issuer, audience)
    }

    suspend inline fun <reified T> RoutingCall.require(): T = this.receiveNullable<T>() ?: throw BadRequestException("Request body is required.")
}