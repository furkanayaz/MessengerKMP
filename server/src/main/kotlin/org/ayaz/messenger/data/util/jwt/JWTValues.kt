package org.ayaz.messenger.data.util.jwt

data class JWTValues(
    val secretKey: String,
    val issuer: String,
    val audience: String
) {
    companion object {
        const val SECRET_KEY = "secretKey"
        const val ISSUER = "issuer"
        const val AUDIENCE = "audience"
    }
}