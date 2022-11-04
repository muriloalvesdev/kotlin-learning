package com.learning.api.core.usecase

import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import com.learning.api.entrypoint.dto.User

class UserUseCase(
    private val gateway: UserGateway
) {
    fun find(
        username: String
    ): User {
        val userEntity = this.gateway.find(username)
            .orElseThrow { UsernameNotFoundException(username) }
        return User.build(userEntity)
    }

    fun save(
        username: String
    ) {
        this.gateway.save(username)
    }
}