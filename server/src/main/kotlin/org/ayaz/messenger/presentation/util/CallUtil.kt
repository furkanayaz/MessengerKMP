package org.ayaz.messenger.presentation.util

import io.ktor.server.application.ApplicationEnvironment
import org.ayaz.messenger.data.util.jwt.JWTValues

object CallUtil {
    fun ApplicationEnvironment.getJWTValues(): JWTValues {
        val secretKey = config.propertyOrNull(JWTValues.SECRET_KEY)?.getString().orEmpty()
        val issuer = config.propertyOrNull(JWTValues.ISSUER)?.getString().orEmpty()
        val audience = config.propertyOrNull(JWTValues.AUDIENCE)?.getString().orEmpty()

        return JWTValues(secretKey, issuer, audience)
    }
}