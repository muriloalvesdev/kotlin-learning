package com.learning.api

import com.learning.api.BaseTest.Companion.PROFILE_TEST
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.mockito.Mockito
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(PROFILE_TEST)
open class BaseTest {

    // MAPPER
    val mapper: UserMapper = UserMapper()

    //GATEWAY
    val gateway: UserGateway = Mockito.mock(UserGateway::class.java)

    //REPOSITORY
    val repository: UserEntityRepository = Mockito.mock(UserEntityRepository::class.java)

    companion object {
        const val PROFILE_TEST: String = "test"
        const val USERNAME_TEST: String = "muriloalvesdev"
    }
}