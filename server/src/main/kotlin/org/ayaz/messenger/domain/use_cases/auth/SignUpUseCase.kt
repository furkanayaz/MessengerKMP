package org.ayaz.messenger.domain.use_cases.auth

import org.ayaz.messenger.data.dto_s.auth.SignUpReqDTO
import org.ayaz.messenger.data.repositories.auth.SignUpRepo
import org.koin.core.annotation.Singleton

@Singleton
class SignUpUseCase(
    private val signUpRepo: SignUpRepo
) {
    operator fun invoke(req: SignUpReqDTO) = signUpRepo.signUp(req)
}