package org.ayaz.messenger.data.di

import com.mongodb.client.MongoDatabase
import org.ayaz.messenger.data.db.DBHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dbModule = module {
    singleOf(::DBHelper)
    single<MongoDatabase> { get<DBHelper>().getInstance() }
}