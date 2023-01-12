package com.learning.api

import com.learning.api.dataprovider.database.entity.UserEntity

interface ConstantsTests {
    companion object {
        const val USERNAME_TEST: String = "muriloalvesdev"
        val entity: UserEntity = UserEntity(username = USERNAME_TEST)
    }
}