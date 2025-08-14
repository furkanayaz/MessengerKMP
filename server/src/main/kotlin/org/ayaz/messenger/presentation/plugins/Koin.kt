package org.ayaz.messenger.presentation.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.ayaz.messenger.data.di.DBModule
import org.ayaz.messenger.domain.di.DomainModule
import org.koin.core.logger.Level
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.installKoin() {
    install(Koin) {
        slf4jLogger(Level.DEBUG)
        modules(DBModule().module, DomainModule().module)
    }
}