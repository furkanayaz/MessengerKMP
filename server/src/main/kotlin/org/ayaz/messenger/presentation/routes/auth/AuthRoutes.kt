package org.ayaz.messenger.presentation.routes.auth

import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receiveNullable
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
import org.ayaz.messenger.presentation.util.CallUtil.getJWTValues
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    post(AuthEndpoints.LOGIN) {
        val reqModel = call.receiveNullable<LoginReqDTO>() ?: throw BadRequestException("")

        if (reqModel.validate()) {
            val loginUseCase: LoginUseCase by inject()
            val jwtUtil: JWTUtil by inject()

            reqModel.phoneNumber = PhoneNumberValidation.getNumber(reqModel.phoneNumber)

            if (loginUseCase(reqModel)) {
                call.respond(Response.Success(item = LoginResDTO(
                    jwtUtil.createToken(call.application.environment.getJWTValues(), reqModel.phoneNumber!!)
                )))
            } else {
                throw BadRequestException("")
            }
        } else {
            throw BadRequestException("")
        }
    }

    post(AuthEndpoints.SIGN_UP) {
        val reqModel = call.receiveNullable<SignUpReqDTO>() ?: throw BadRequestException("")

        if (reqModel.validate()) {
            val signUpUseCase: SignUpUseCase by inject()

            reqModel.phoneNumber = PhoneNumberValidation.getNumber(reqModel.phoneNumber)

            if (signUpUseCase(reqModel)) {
                call.respond(Response.Success(item = null))
            } else {
                throw BadRequestException("")
            }
        } else {
            throw BadRequestException("")
        }
    }
}