package com.learning.api.core.gateway

import com.learning.api.dataprovider.database.entity.UserEntity
import java.util.Optional

interface UserGateway {
    fun find(username: String): Optional<UserEntity>
    fun save(username: String)
}