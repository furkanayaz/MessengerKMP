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
import org.ayaz.messenger.data.util.validations.PhoneNumberValidation
import org.ayaz.messenger.domain.use_cases.auth.LoginUseCase
import org.ayaz.messenger.domain.use_cases.auth.SignUpUseCase
import org.ayaz.messenger.domain.util.Resource
import org.ayaz.messenger.presentation.util.CallUtil.getJWTValues
import org.ayaz.messenger.presentation.util.CallUtil.require
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    post(AuthEndpoints.LOGIN) {
        val reqModel = call.require<LoginReqDTO>()

        with(reqModel.validate()) {
            onSuccess {
                val loginUseCase: LoginUseCase by inject()
                val jwtUtil: JWTUtil by inject()

                reqModel.phoneNumber = PhoneNumberValidation.getNumber(reqModel.phoneNumber)

                val response = loginUseCase(reqModel)

                when(response) {
                    is Resource.Error<Boolean> -> call.respond(HttpStatusCode.BadRequest, Response.Error(description = response.message))
                    is Resource.Success<Boolean> -> call.respond(
                        HttpStatusCode.OK, Response.Success(
                            item = LoginResDTO(
                                jwtUtil.createToken(
                                    call.application.environment.getJWTValues(), reqModel.phoneNumber!!
                                )
                            )
                        )
                    )
                }
            }

            onFailure { throw it }
        }
    }

    post(AuthEndpoints.SIGN_UP) {
        val reqModel = call.require<SignUpReqDTO>()

        with(reqModel.validate()) {
            onSuccess {
                val signUpUseCase: SignUpUseCase by inject()

                reqModel.phoneNumber = PhoneNumberValidation.getNumber(reqModel.phoneNumber)

                val response = signUpUseCase(reqModel)

                when(response) {
                    is Resource.Error<Boolean> -> call.respond(HttpStatusCode.BadRequest, Response.Error(description = response.message))
                    is Resource.Success<Boolean> -> call.respond(HttpStatusCode.OK, Response.Success(item = null))
                }
            }

            onFailure { throw it }
        }
    }
}