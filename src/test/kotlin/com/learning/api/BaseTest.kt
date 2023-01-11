package com.learning.api

import com.learning.api.ConstantsTests.Companion.PROFILE_TEST
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.junit.jupiter.api.Tag
import org.mockito.Mockito
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(PROFILE_TEST)
@Tag("unit-tests")
open class BaseTest {

    // MAPPER
    val mapper: UserMapper = UserMapper()

    //GATEWAY
    val gateway: UserGateway = Mockito.mock(UserGateway::class.java)

    //REPOSITORY
    val repository: UserEntityRepository = Mockito.mock(UserEntityRepository::class.java)
}