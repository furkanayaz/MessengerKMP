package org.ayaz.messenger.data.db.collections.user

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase


object UserCollection {
    private const val COLLECTION_NAME = "Users"
    private lateinit var userCollection: MongoCollection<UserEntity>

    fun MongoDatabase.getUserCollection(): MongoCollection<UserEntity> {
        if (::userCollection.isInitialized.not()) {
            userCollection = getCollection(COLLECTION_NAME, UserEntity::class.java)
        }

        return userCollection
    }
}