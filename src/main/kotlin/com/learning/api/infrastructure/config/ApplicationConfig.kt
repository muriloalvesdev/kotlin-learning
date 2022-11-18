package com.learning.api.infrastructure.config

import com.learning.api.core.gateway.UserGateway
import com.learning.api.core.usecase.CreateUserUseCase
import com.learning.api.core.usecase.FindUserUseCase
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.gateway.UserGatewayImpl
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig(
    private val repository: UserEntityRepository
) {

    @Bean
    @ConditionalOnMissingBean(CreateUserUseCase::class)
    fun createUserUseCase(
        gateway: UserGateway,
    ): CreateUserUseCase {
        return CreateUserUseCase(gateway)
    }

    @Bean
    @ConditionalOnMissingBean(FindUserUseCase::class)
    fun findUserUseCase(
        gateway: UserGateway
    ): FindUserUseCase {
        return FindUserUseCase(gateway)
    }

    @Bean
    @ConditionalOnMissingBean(UserGateway::class)
    fun userGateway(mapper: UserMapper): UserGateway {
        return UserGatewayImpl(this.repository, mapper)
    }

    @Bean
    @ConditionalOnMissingBean(UserMapper::class)
    fun userMapper() = UserMapper()
}