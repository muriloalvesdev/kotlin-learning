package com.learning.api.entrypoint.api

import com.learning.api.BaseIntegrationTests
import com.learning.api.core.domain.user.User
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get

class FindUserControllerIntegrationTest : BaseIntegrationTests() {

    @Test
    fun shouldFind() {
        mockMvc.get("/users/$USERNAME_TEST")
            .andExpect {
                status { isOk() }
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(objectMapper.writeValueAsString(User(USERNAME_TEST))) }
            }

        verify(respository, times(1))
            .findByUsername(anyString())
    }
}