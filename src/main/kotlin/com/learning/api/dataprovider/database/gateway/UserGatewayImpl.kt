package com.learning.api.dataprovider.database.gateway

import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import java.util.Optional

class UserGatewayImpl(
    private val repository: UserEntityRepository
) : UserGateway {

    override fun find(username: String): Optional<UserEntity> {
        return this.repository.findByUsername(username)
    }

    override fun save(username: String) {
        if (this.repository.existsByUsername(username)) throw UserAlreadyExistsException(username)

        this.repository.save(UserEntity(username))
    }
}