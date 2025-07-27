package org.ayaz.messenger.presentation.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.ayaz.messenger.presentation.routes.auth.authRoutes

fun Application.installRouting() {
    routing {
        authRoutes()
    }
}