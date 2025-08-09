package org.ayaz.messenger.data.repositories.auth

import com.mongodb.client.MongoDatabase
import org.ayaz.messenger.data.db.collections.user.UserCollection.getUserCollection
import org.ayaz.messenger.data.db.collections.user.UserEntity
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.domain.util.Resource
import org.litote.kmongo.eq

fun interface ILoginRepo {
    suspend fun login(req: LoginReqDTO): Resource<Boolean>
}

class LoginRepo(
    private val database: MongoDatabase
): ILoginRepo {
    override suspend fun login(req: LoginReqDTO): Resource<Boolean> {
        val canUserLogin = database.getUserCollection().find(UserEntity::phoneNumber eq req.phoneNumber).singleOrNull()
        return if (canUserLogin != null) Resource.Success(true) else Resource.Error("No matching record found for the phone number you entered.")
    }
}