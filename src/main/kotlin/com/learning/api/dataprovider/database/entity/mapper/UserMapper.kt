package com.learning.api.dataprovider.database.entity.mapper

import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.entity.UserEntity

class UserMapper {
    fun toUserDomain(entity: UserEntity) = User(entity.username)
    fun toUserEntity(domain: User) = UserEntity(domain.username)
}