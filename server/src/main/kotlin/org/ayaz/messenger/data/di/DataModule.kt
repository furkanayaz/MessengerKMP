package org.ayaz.messenger.data.di

import org.ayaz.messenger.data.repositories.auth.ILoginRepo
import org.ayaz.messenger.data.repositories.auth.LoginRepo
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(dbModule)
    singleOf(::LoginRepo) bind ILoginRepo::class
    //single<ILoginRepo> { LoginRepo(get()) }
    //singleOf(::LoginRepo) bind ILoginRepo::class
}