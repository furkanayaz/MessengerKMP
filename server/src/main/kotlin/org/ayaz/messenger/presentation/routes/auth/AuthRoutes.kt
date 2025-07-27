package org.ayaz.messenger.presentation.routes.auth

import io.ktor.server.auth.authenticate
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.data.dto_s.auth.LoginResDTO
import org.ayaz.messenger.data.dto_s.auth.SignUpReqDTO
import org.ayaz.messenger.data.util.jwt.JWTUtil
import org.ayaz.messenger.data.util.Response
import org.ayaz.messenger.domain.use_cases.auth.LoginUseCase
import org.ayaz.messenger.domain.use_cases.auth.SignUpUseCase
import org.ayaz.messenger.presentation.util.CallUtil.getJWTValues
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    authenticate {
        post(AuthEndpoints.LOGIN) {
            val reqModel = call.receiveNullable<LoginReqDTO>() ?: throw IllegalArgumentException()

            if (reqModel.validate()) {
                val loginUseCase: LoginUseCase by inject()
                val jwtUtil: JWTUtil by inject()

                if (loginUseCase(reqModel)) {
                    call.respond(Response.Success(item = LoginResDTO(
                        jwtUtil.createToken(call.getJWTValues(), reqModel.phoneNumber!!)
                    )))
                } else {
                    call.respond(Response.Error(errorCode = 400, description = "Your entered phone number is not found."))
                }
            } else {
                call.respond(Response.Error(errorCode = 400, description = "Your entered field(s) is not null or empty."))
            }
        }
    }

    post(AuthEndpoints.SIGN_UP) {
        val reqModel = call.receiveNullable<SignUpReqDTO>() ?: throw IllegalArgumentException()

        if (reqModel.validate()) {
            val signUpUseCase: SignUpUseCase by inject()
            if (signUpUseCase(reqModel)) {
                call.respond(Response.Success(item = null))
            } else {
                call.respond(Response.Error(errorCode = 400, description = "Occurred an unknown error."))
            }
        } else {
            call.respond(Response.Error(errorCode = 400, description = "Your entered fields cannot valid."))
        }
    }
}