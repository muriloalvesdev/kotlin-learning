package com.learning.api

import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.junit.jupiter.api.Tag
import org.mockito.Mockito

@Tag("unit-tests")
open class BaseUnitTest {

    // MAPPER
    protected val mapper: UserMapper = UserMapper()

    //GATEWAY
    protected val gateway: UserGateway = Mockito.mock(UserGateway::class.java)

    //REPOSITORY
    protected val repository: UserEntityRepository = Mockito.mock(UserEntityRepository::class.java)
}