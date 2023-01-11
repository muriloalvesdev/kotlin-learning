package com.learning.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.api.ConstantsTests.Companion.PROFILE_TEST
import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import java.util.Optional

@ActiveProfiles(PROFILE_TEST)
@Tag("integration-tests")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @MockBean
    protected lateinit var respository: UserEntityRepository

    @BeforeEach
    fun setup() {
        whenever(
            this.respository.save(UserEntity(USERNAME_TEST))
        ).thenAnswer {
            it.arguments.first()
        }

        whenever(
            this.respository.findByUsername(USERNAME_TEST)
        ).thenAnswer {
            Optional.ofNullable(UserEntity(USERNAME_TEST))
        }
    }
}