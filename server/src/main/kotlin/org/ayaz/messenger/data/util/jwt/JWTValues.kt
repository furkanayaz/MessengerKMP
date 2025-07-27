package org.ayaz.messenger.data.util.jwt

data class JWTValues(
    val secretKey: String,
    val issuer: String,
    val audience: String
)
