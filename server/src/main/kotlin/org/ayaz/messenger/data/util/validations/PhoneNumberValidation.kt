package org.ayaz.messenger.data.util.validations

object PhoneNumberValidation {
    fun validate(phoneNumber: String?): Boolean {
        return when {
            phoneNumber.isNullOrEmpty() -> false
            phoneNumber.trim().startsWith("0") && phoneNumber.length == 11 -> true
            phoneNumber.trim().startsWith("+90") && phoneNumber.length == 13 -> true
            phoneNumber.trim().length == 10 -> true
            else -> false
        }
    }
}