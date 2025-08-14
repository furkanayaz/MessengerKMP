package org.ayaz.messenger.data.repositories.auth

import com.mongodb.client.MongoCollection
import org.ayaz.messenger.data.entities.user.UserEntity
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.domain.util.Resource
import org.koin.core.annotation.Singleton
import org.litote.kmongo.eq

fun interface ILoginRepo {
    suspend fun login(req: LoginReqDTO): Resource<Boolean>
}

@Singleton
class LoginRepo(
    private val collection: MongoCollection<UserEntity>
): ILoginRepo {
    override suspend fun login(req: LoginReqDTO): Resource<Boolean> {
        val canUserLogin = collection.find(UserEntity::phoneNumber eq req.phoneNumber).singleOrNull()
        return if (canUserLogin != null) Resource.Success(true) else Resource.Error("No matching record found for the phone number you entered.")
    }
}