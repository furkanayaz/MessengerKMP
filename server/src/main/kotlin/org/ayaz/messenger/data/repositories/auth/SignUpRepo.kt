package org.ayaz.messenger.data.repositories.auth

import com.mongodb.client.MongoDatabase
import org.ayaz.messenger.data.db.collections.user.UserCollection.getUserCollection
import org.ayaz.messenger.data.db.collections.user.UserEntity
import org.ayaz.messenger.data.dto_s.auth.SignUpReqDTO

fun interface ISignUpRepo {
    fun signUp(req: SignUpReqDTO): Boolean
}

class SignUpRepo(private val db: MongoDatabase): ISignUpRepo {
    override fun signUp(req: SignUpReqDTO): Boolean {
        return db.getUserCollection().insertOne(UserEntity(req.name!!, req.lastName!!, req.phoneNumber!!)).wasAcknowledged()
    }
}