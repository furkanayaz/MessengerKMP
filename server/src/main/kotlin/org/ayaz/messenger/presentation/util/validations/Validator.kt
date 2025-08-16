package org.ayaz.messenger.presentation.util.validations

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.koin.core.annotation.Singleton

@Singleton
class Validator {
    private lateinit var validator: Validator

    fun <T: Any> validate(item: T) {
        if (::validator.isInitialized.not()) {
            validator = Validation.buildDefaultValidatorFactory().validator
        }

        val exceptionsMessages = validator.validate(item)

        if (exceptionsMessages.isNotEmpty()) throw ConstraintViolationException(exceptionsMessages)
    }
}