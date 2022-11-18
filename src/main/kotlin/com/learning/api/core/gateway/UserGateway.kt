package com.learning.api.core.gateway

import com.learning.api.core.domain.user.User
import java.util.Optional

interface UserGateway {
    fun find(username: String): Optional<User>
    fun save(domain: User)
}