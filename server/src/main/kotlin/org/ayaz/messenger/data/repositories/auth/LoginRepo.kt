package org.ayaz.messenger.data.repositories.auth

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.ayaz.messenger.data.entities.user.UserEntity
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.domain.util.encryption.PasswordEncryption
import org.ayaz.messenger.domain.util.Resource
import org.koin.core.annotation.Singleton
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

fun interface ILoginRepo {
    suspend fun login(req: LoginReqDTO): Resource<Boolean>
}

@Singleton
class LoginRepo(
    private val collection: MongoCollection<UserEntity>,
    private val passwordEncryption: PasswordEncryption
): ILoginRepo {
    override suspend fun login(req: LoginReqDTO): Resource<Boolean> {
        val userSaltValue = collection.findOne(UserEntity::email eq req.email)?.salt ?: return Resource.Error(listOf("Your entered email address cannot found."))
        val encryptedPassword = passwordEncryption.encodeWithSalt(userSaltValue, req.password)
        val canUserLogin = collection.find(Filters.and(UserEntity::email eq req.email, UserEntity::password eq encryptedPassword)).singleOrNull()
        return if (canUserLogin != null) Resource.Success(true) else Resource.Error(listOf("Your entered password is wrong."))
    }
}