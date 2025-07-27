package org.ayaz.messenger.presentation.routes.auth

import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.data.dto_s.auth.SignUpReqDTO
import org.ayaz.messenger.data.util.Response
import org.ayaz.messenger.domain.use_cases.auth.LoginUseCase
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val loginUseCase: LoginUseCase by inject()

    post(AuthEndpoints.LOGIN) {
        val reqModel = call.receiveNullable<LoginReqDTO>()

        if (reqModel?.validate() == true) {
            call.respond(
                if (loginUseCase(reqModel)) {
                    Response.Success<Any>()
                } else {
                    Response.Error(errorCode = 400, description = "Your entered phone number is not found.")
                }
            )
        } else {
            call.respond(Response.Error(errorCode = 400, description = "Your entered field(s) is not null or empty."))
        }
    }

    post(AuthEndpoints.SIGN_UP) {
        val reqModel = call.receiveNullable<SignUpReqDTO>()

    }
}