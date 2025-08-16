package org.ayaz.messenger.data.entities.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class UserEntity(
    val name: String,
    @BsonProperty("last_name")
    val lastName: String,
    val email: String,
    val password: String,
    @BsonId @BsonProperty("_id") val id: ObjectId = ObjectId()
)