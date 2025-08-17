package org.ayaz.messenger.presentation.routes.auth

import io.ktor.http.HttpStatusCode
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
import org.ayaz.messenger.domain.util.Resource
import org.ayaz.messenger.presentation.util.CallUtil.getJWTValues
import org.ayaz.messenger.presentation.util.CallUtil.require
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    post(AuthEndpoints.LOGIN) {
        val reqModel = call.require<LoginReqDTO>()
        val loginUseCase: LoginUseCase by inject()
        val jwtUtil: JWTUtil by inject()

        when(val response = loginUseCase(reqModel)) {
            is Resource.Error<Boolean> -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorMessages = response.messages))
            is Resource.Success<Boolean> -> call.respond(
                HttpStatusCode.OK, Response.Success(
                    item = LoginResDTO(
                        jwtUtil.createToken(
                            call.application.environment.getJWTValues(), reqModel.email, reqModel.password
                        )
                    )
                )
            )
        }
    }

    post(AuthEndpoints.SIGN_UP) {
        val reqModel = call.require<SignUpReqDTO>()
        val signUpUseCase: SignUpUseCase by inject()

        when(val response = signUpUseCase(reqModel)) {
            is Resource.Error<Boolean> -> call.respond(HttpStatusCode.BadRequest, Response.Error(errorMessages = response.messages))
            is Resource.Success<Boolean> -> call.respond(HttpStatusCode.OK, Response.Success(item = null, informationMessage = "Your account is created."))
        }
    }
}