package org.ayaz.messenger.domain.util.encryption

import org.koin.core.annotation.Singleton
import java.security.MessageDigest
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Singleton
class PasswordEncryption {
    private companion object {
        const val ALGORITHM = "SHA-256"
    }

    private lateinit var messageDigest: MessageDigest

    init {
        if (::messageDigest.isInitialized.not()) messageDigest = MessageDigest.getInstance(ALGORITHM)
    }

    @OptIn(ExperimentalUuidApi::class)
    fun encode(rawValue: String): EncryptedPassword {
        val saltValue = Uuid.Companion.random().toHexString()
        val encodedValue = messageDigest.digest((saltValue + rawValue).toByteArray()).toHexString()
        return EncryptedPassword(saltValue, encodedValue)
    }

    fun encodeWithSalt(saltValue: String, rawValue: String) = messageDigest.digest((saltValue + rawValue).toByteArray()).toHexString()
}