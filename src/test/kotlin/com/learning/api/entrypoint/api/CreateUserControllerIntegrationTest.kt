package com.learning.api.entrypoint.api

import com.learning.api.BaseIntegrationTests
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.providers.UserProviderTests
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class CreateUserControllerIntegrationTest : BaseIntegrationTests() {

    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSave(domain: User) {

        mockMvc.post("/users/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(domain)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
        }

        verify(respository, times(1))
            .save(any(UserEntity::class.java))
    }
}