package org.ayaz.messenger.data.util.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date
import kotlin.time.ExperimentalTime

class JWTUtil {
    private companion object {
        const val PHONE_NUMBER = "phoneNumber"
        const val EXPIRE_MILLIS = 1000 * 60 * 60 * 24
    }

    @OptIn(ExperimentalTime::class)
    fun createToken(values: JWTValues, phoneNumber: String): String =
        JWT.create()
            .withIssuer(values.issuer)
            .withAudience(values.audience)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRE_MILLIS))
            .withClaim(PHONE_NUMBER, phoneNumber)
            .sign(Algorithm.HMAC256(values.secretKey))

    fun verifyToken(values: JWTValues, phoneNumber: String): JWTVerifier =
        JWT.require(Algorithm.HMAC256(values.secretKey))
            .withIssuer(values.issuer)
            .withAudience(values.audience)
            .withClaim(PHONE_NUMBER, phoneNumber)
            .build()

    fun validateToken(credential: JWTCredential): JWTPrincipal? {
        return if (credential.payload.getClaim(PHONE_NUMBER).asString().isNullOrEmpty()) {
            null
        } else {
            JWTPrincipal(credential.payload)
        }
    }
}