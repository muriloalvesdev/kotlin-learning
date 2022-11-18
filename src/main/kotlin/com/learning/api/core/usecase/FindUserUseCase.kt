package com.learning.api.core.usecase

import com.learning.api.core.domain.user.User
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException

class FindUserUseCase(
    private val gateway: UserGateway
) {

    fun find(
        username: String
    ): User {
        return this.gateway.find(username)
            .orElseThrow { UsernameNotFoundException(username) }
    }
}