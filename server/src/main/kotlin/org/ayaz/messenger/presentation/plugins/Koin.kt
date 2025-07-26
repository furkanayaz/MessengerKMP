package org.ayaz.messenger.presentation.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.ayaz.messenger.data.di.dataModule
import org.ayaz.messenger.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.ktor.plugin.Koin

fun Application.installKoin() {
    install(Koin) {
        startKoin {
            modules(dataModule, domainModule)
        }
    }
}