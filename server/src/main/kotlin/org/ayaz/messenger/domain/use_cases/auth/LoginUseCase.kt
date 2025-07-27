package org.ayaz.messenger.domain.use_cases.auth

import org.ayaz.messenger.data.dto_s.auth.LoginReqDTO
import org.ayaz.messenger.data.repositories.auth.LoginRepo

class LoginUseCase(
    private val loginRepo: LoginRepo
) {
    suspend operator fun invoke(req: LoginReqDTO) = loginRepo.login(req)
}