package org.ayaz.messenger.presentation

import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.ayaz.messenger.presentation.plugins.installContentNegotiation
import org.ayaz.messenger.presentation.plugins.installCors
import org.ayaz.messenger.presentation.plugins.installKoin
import org.ayaz.messenger.presentation.plugins.installLogging
import org.ayaz.messenger.presentation.plugins.installRouting
import org.ayaz.messenger.presentation.plugins.installStatusPages

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    //installStatusPages()
    installKoin()
    installCors()
    installLogging()
    installContentNegotiation()
    installRouting()
}