package org.ayaz.messenger.data.util.validations

import io.ktor.server.plugins.BadRequestException

object PhoneNumberValidation {
    private const val ERROR_MESSAGE = "Please enter a valid phone number."

    /**
     * Simplifies the phone number format.
     * */
    fun getNumber(phoneNumber: String?): String? = phoneNumber?.trim()?.replace(" ", "")

    /**
     * Validates the phone number format.
     * */
    fun validate(phoneNumber: String?): Result<Boolean> {
        val replacedPhoneNumber = phoneNumber?.trim()?.replace(" ", "")

        return when {
            replacedPhoneNumber.isNullOrEmpty() -> Result.failure(BadRequestException(ERROR_MESSAGE))
            replacedPhoneNumber.startsWith("0") && replacedPhoneNumber.length == 11 -> Result.success(true)
            replacedPhoneNumber.startsWith("+90") && replacedPhoneNumber.length == 13 -> Result.success(true)
            replacedPhoneNumber.length == 10 -> Result.success(true)
            else -> Result.failure(BadRequestException(ERROR_MESSAGE))
        }
    }
}