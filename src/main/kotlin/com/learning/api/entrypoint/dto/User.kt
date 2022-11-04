package com.learning.api.entrypoint.dto

import com.learning.api.dataprovider.database.entity.UserEntity

data class User(
    val username: String
) {
    companion object {
        @JvmStatic
        fun build(entity: UserEntity): User {
            return User(entity.username())
        }
    }
}