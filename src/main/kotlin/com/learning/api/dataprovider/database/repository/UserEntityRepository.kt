package com.learning.api.dataprovider.database.repository

import com.learning.api.dataprovider.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface UserEntityRepository : JpaRepository<UserEntity, UUID> {
    fun findByUsername(username: String): Optional<UserEntity>
    fun existsByUsername(username: String): Boolean
}