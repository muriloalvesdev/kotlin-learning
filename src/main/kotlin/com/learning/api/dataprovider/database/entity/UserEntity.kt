package com.learning.api.dataprovider.database.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    val uuid: UUID = UUID.randomUUID(),
    
    @Column(name = "username", unique = true)
    val username: String,
)