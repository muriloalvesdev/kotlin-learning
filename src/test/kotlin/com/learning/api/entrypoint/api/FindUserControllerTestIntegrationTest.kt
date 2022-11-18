package com.learning.api.entrypoint.api

import com.learning.api.BaseTest.Companion.USERNAME_TEST
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.providers.UserEntityProviderTests
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class FindUserControllerTestIntegrationTest : BaseIntegrationTests() {

    @BeforeEach
    fun cleanDatabase() {
        this.repository!!.deleteAll()
    }

    @DisplayName(
        "Deve fazer uma requisicao HTTP " +
            "com o verbo GET e retornar o username do usuario"
    )
    @ParameterizedTest
    @ArgumentsSource(UserEntityProviderTests::class)
    fun shouldFindWithSuccess(entity: UserEntity) {
        //GIVEN
        this.repository!!.save(entity)

        //WHEN
        this.mockMvc!!.perform(
            get(BASE_URL.plus("/$USERNAME_TEST"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

            //THEN
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.username", `is`(USERNAME_TEST)))
    }
}