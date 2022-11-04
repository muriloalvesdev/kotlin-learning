package com.learning.api.entrypoint.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import com.learning.api.utils.ConstantsTests
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ActiveProfiles(ConstantsTests.PROFILE_TEST)
@AutoConfigureMockMvc
class BaseIntegrationTests {

    @Autowired
    val webApplicationContext: WebApplicationContext? = null

    @Autowired
    val repository: UserEntityRepository? = null

    @Autowired
    val mapper: ObjectMapper? = null

    var mockMvc: MockMvc? = null

    companion object {
        const val BASE_URL: String = "/users"
    }

    @BeforeEach
    fun setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext!!).build()
    }
}