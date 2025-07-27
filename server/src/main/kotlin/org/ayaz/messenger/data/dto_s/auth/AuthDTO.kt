package org.ayaz.messenger.data.dto_s.auth

import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class LoginReqDTO(
    val phoneNumber: String? = null
) {
    fun validate(): Boolean {
        return when {
            phoneNumber.isNullOrEmpty() -> false
            phoneNumber.trim().startsWith("0") && phoneNumber.length == 11 -> true
            phoneNumber.trim().startsWith("+90") && phoneNumber.length == 13 -> true
            phoneNumber.trim().length == 10 -> true
            else -> false
        }
    }

    fun getGSMCode(): Int = Random.nextBits(6)
}

@Serializable
data class LoginResDTO(
    val token: String
)

@Serializable
data class SignUpReqDTO(
    val name: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
)