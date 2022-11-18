package com.learning.api.dataprovider.database.gateway

import com.learning.api.core.domain.user.User
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import java.util.Optional

class UserGatewayImpl(
    private val repository: UserEntityRepository,
    private val mapper: UserMapper
) : UserGateway {

    override fun find(
        username: String
    ): Optional<User> = this.repository.findByUsername(username).map { this.mapper.toUserDomain(it) }

    override fun save(
        domain: User
    ) {
        if (this.repository.existsByUsername(domain.username)) throw UserAlreadyExistsException(domain.username)

        val entity = this.mapper.toUserEntity(domain)
        this.repository.save(entity)
    }
}