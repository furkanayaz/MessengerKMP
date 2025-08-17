package org.ayaz.messenger.data.di

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.IndexOptions
import org.ayaz.messenger.data.entities.user.UserEntity
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import org.litote.kmongo.KMongo
import org.litote.kmongo.ascending

@Module
@ComponentScan("org.ayaz.messenger.data")
class DBModule {
    private companion object {
        const val CONN_URL = "mongodb://localhost:27017"
        const val DB_NAME = "Messenger"

        const val USERS_COLLECTION = "Users"
    }

    @Singleton
    fun provideMongoDB(): MongoDatabase = KMongo.createClient(CONN_URL).getDatabase(DB_NAME)

    @Singleton
    fun provideUserCollection(mongoDatabase: MongoDatabase): MongoCollection<UserEntity> {
        val userCollection = mongoDatabase.getCollection(USERS_COLLECTION, UserEntity::class.java)
        userCollection.createIndex(ascending(UserEntity::email), IndexOptions().unique(true))
        return userCollection
    }
}