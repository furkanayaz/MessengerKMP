package org.ayaz.messenger.presentation.util

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.ApplicationEnvironment
import org.ayaz.messenger.data.util.jwt.JWTValues

object CallUtil {
    fun ApplicationEnvironment.getJWTValues(): JWTValues {
        val secretKey = config.propertyOrNull("secret-key")?.getString().orEmpty()
        val issuer = config.propertyOrNull("issuer")?.getString().orEmpty()
        val audience = config.propertyOrNull("audience")?.getString().orEmpty()

        return JWTValues(secretKey, issuer, audience)
    }
}