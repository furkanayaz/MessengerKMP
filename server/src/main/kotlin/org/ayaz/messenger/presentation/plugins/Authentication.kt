package org.ayaz.messenger.presentation.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import org.ayaz.messenger.data.util.jwt.JWTUtil
import org.ayaz.messenger.presentation.util.CallUtil.getJWTValues
import org.koin.ktor.ext.inject

fun Application.installAuthentication() {
    val jwtUtil: JWTUtil by inject()
    val jwtValues = environment.getJWTValues()

    install(Authentication) {
        jwt {
            verifier {
                jwtUtil.verifyToken(jwtValues, "05539759957")
            }

            validate {

            }

            challenge {

            }
        }
    }
}