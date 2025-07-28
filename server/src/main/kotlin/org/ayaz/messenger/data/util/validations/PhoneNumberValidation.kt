package org.ayaz.messenger.data.util.validations

object PhoneNumberValidation {
    fun getNumber(phoneNumber: String?): String? = phoneNumber?.trim()?.replace(" ", "")

    fun validate(phoneNumber: String?): Boolean {
        val replacedPhoneNumber = phoneNumber?.trim()?.replace(" ", "")

        return when {
            replacedPhoneNumber.isNullOrEmpty() -> false
            replacedPhoneNumber.startsWith("0") && replacedPhoneNumber.length == 11 -> true
            replacedPhoneNumber.startsWith("+90") && replacedPhoneNumber.length == 13 -> true
            replacedPhoneNumber.length == 10 -> true
            else -> false
        }
    }
}