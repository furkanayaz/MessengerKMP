package org.ayaz.messenger.data.repositories.auth

import com.mongodb.client.MongoDatabase
import org.ayaz.messenger.data.db.collections.user.UserCollection.getUserCollection
import org.ayaz.messenger.data.db.collections.user.UserEntity
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.litote.kmongo.eq

fun interface ILoginRepo {
    suspend fun login(req: LoginReqDTO): Boolean
}

class LoginRepo(
    private val database: MongoDatabase
): ILoginRepo {
    override suspend fun login(req: LoginReqDTO): Boolean {
        return database.getUserCollection().find(UserEntity::phoneNumber eq req.phoneNumber).singleOrNull() != null
    }
}