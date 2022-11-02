package com.learning.api.dataprovider.database.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "username", unique = true)
    private val username: String
) {
    @Id
    private val uuid: UUID? = UUID.randomUUID()

    fun uuid(): UUID {
        return uuid!!
    }

    fun username(): String {
        return this.username
    }
}