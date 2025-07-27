package org.ayaz.messenger.domain.di

import org.ayaz.messenger.data.di.dataModule
import org.ayaz.messenger.domain.use_cases.auth.LoginUseCase
import org.ayaz.messenger.domain.use_cases.auth.SignUpUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    includes(dataModule)
    singleOf(::LoginUseCase)
    singleOf(::SignUpUseCase)
}