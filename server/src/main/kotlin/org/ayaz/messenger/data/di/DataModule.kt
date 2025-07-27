package org.ayaz.messenger.data.di

import org.ayaz.messenger.data.repositories.auth.ILoginRepo
import org.ayaz.messenger.data.repositories.auth.ISignUpRepo
import org.ayaz.messenger.data.repositories.auth.LoginRepo
import org.ayaz.messenger.data.repositories.auth.SignUpRepo
import org.ayaz.messenger.data.util.jwt.JWTUtil
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(dbModule)
    singleOf(::LoginRepo) bind ILoginRepo::class
    singleOf(::SignUpRepo) bind ISignUpRepo::class
    singleOf(::JWTUtil)
    //single<ILoginRepo> { LoginRepo(get()) }
    //singleOf(::LoginRepo) bind ILoginRepo::class
}