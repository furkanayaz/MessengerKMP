package org.ayaz.messenger.data.dto_s.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import kotlinx.serialization.Serializable
import org.ayaz.messenger.presentation.util.validations.annotations.Password

@Serializable
data class LoginReqDTO(
    @Email(message = "email")
    var email: String,
    @Size(min = 8, max = 20)
    var password: String
)

@Serializable
data class LoginResDTO(
    val token: String
)

@Serializable
data class SignUpReqDTO(
    @Size(min = 2)
    val name: String,
    @Size(min = 2)
    val lastName: String,
    @Email
    val email: String,
    @Size(min = 8, max = 20)
    @Password
    val password: String
)