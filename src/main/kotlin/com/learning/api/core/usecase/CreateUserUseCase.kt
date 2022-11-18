package com.learning.api.core.usecase

import com.learning.api.core.domain.user.User
import com.learning.api.core.gateway.UserGateway

class CreateUserUseCase(
    private val gateway: UserGateway,
) {
    fun save(
        domain: User
    ) {
        this.gateway.save(domain)
    }
}