package org.ayaz.messenger.data.dto_s.auth

import kotlinx.serialization.Serializable
import org.ayaz.messenger.data.util.validations.PhoneNumberValidation
import kotlin.random.Random

@Serializable
data class LoginReqDTO(
    var phoneNumber: String? = null
) {
    fun validate() = PhoneNumberValidation.validate(phoneNumber)
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
    var phoneNumber: String? = null,
) {
    fun validate() = (name.isNullOrEmpty().not() && name.length >=3) && (lastName.isNullOrEmpty().not() && lastName.length >=3) && PhoneNumberValidation.validate(phoneNumber)
}