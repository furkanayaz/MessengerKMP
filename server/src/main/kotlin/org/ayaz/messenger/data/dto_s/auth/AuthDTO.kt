package org.ayaz.messenger.data.dto_s.auth

import io.ktor.server.plugins.BadRequestException
import kotlinx.serialization.Serializable
import org.ayaz.messenger.data.util.validations.PhoneNumberValidation
import kotlin.random.Random

@Serializable
data class LoginReqDTO(
    var phoneNumber: String? = null
) {
    fun validate(): Result<Boolean> = PhoneNumberValidation.validate(phoneNumber)
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
    fun validate(): Result<Boolean> {
        val isNameValid = (name.isNullOrEmpty().not() && name.length >=3)
        val isLastNameValid = (lastName.isNullOrEmpty().not() && lastName.length >=3)
        val isPhoneNumberValid = PhoneNumberValidation.validate(phoneNumber)

        if (isNameValid && isLastNameValid && isPhoneNumberValid.isSuccess) return Result.success(true)

        val errorMessage = buildString {
            if (isNameValid.not()) append("Please enter a valid name.")
            if (isLastNameValid.not()) append("Please enter a valid last name.")
            isPhoneNumberValid.onFailure { append(it.message.orEmpty()) }
        }

        return Result.failure(BadRequestException(errorMessage))
    }
}