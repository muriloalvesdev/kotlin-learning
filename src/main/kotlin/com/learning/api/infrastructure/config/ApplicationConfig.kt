package com.learning.api.infrastructure.config

import com.learning.api.core.gateway.UserGateway
import com.learning.api.core.usecase.UserUseCase
import com.learning.api.dataprovider.database.gateway.UserGatewayImpl
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig(
    private val repository: UserEntityRepository
) {

    @Bean
    fun userUseCase(): UserUseCase {
        return UserUseCase(this.userGateway())
    }

    @Bean
    fun userGateway(): UserGateway {
        return UserGatewayImpl(this.repository)
    }

}