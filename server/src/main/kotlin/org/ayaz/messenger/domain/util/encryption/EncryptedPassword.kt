package org.ayaz.messenger.domain.util.encryption

data class EncryptedPassword(
    val saltValue: String,
    val encodedPassword: String
)
