package org.ayaz.messenger.data.db

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

class DBHelper {
    private companion object {
        const val CONN_URL = "mongodb://localhost:27017"
        const val DB_NAME = "Messenger"
    }

    private lateinit var dbInstance: MongoDatabase

    fun getInstance(): MongoDatabase {
        if (::dbInstance.isInitialized.not()) {
            dbInstance = KMongo.createClient(CONN_URL).getDatabase(DB_NAME)
        }

        return dbInstance
    }
}