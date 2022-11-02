package com.learning.api.entrypoint.dto

import com.learning.api.dataprovider.database.entity.UserEntity

data class User(
    private val username: String
) {
    fun username(): String {
        return this.username
    }

    companion object {
        @JvmStatic
        fun build(entity: UserEntity): User {
            return User(entity.username())
        }
    }
}