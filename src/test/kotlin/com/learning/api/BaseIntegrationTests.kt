package com.learning.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.core.domain.user.User
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.mapper.UserMapper
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@Tag("integration-tests")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var gateway: UserGateway

    protected val mapper: UserMapper = UserMapper()

    fun requestGet(username: String): ResultActionsDsl {
        return mockMvc.get("/users/$USERNAME_TEST")
    }

    fun requestPost(body: User): ResultActionsDsl {
        return mockMvc.post("/users/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(body)
            accept = MediaType.APPLICATION_JSON
        }
    }
}