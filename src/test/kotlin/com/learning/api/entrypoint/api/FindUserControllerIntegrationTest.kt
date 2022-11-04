package com.learning.api.entrypoint.api

import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class FindUserControllerIntegrationTest : BaseIntegrationTests() {

    @BeforeEach
    fun cleanDatabase() {
        this.repository!!.deleteAll()
    }

    @DisplayName(
        "Deve fazer uma requisicao HTTP " +
        "com o verbo GET e retornar o username do usuario"
    )
    @Test
    fun shouldFindWithSuccess() {
        //GIVEN
        this.repository!!.save(UserEntity(USERNAME_TEST))


        //WHEN
        this.mockMvc!!.perform(
            MockMvcRequestBuilders.get(BASE_URL.plus("/").plus(USERNAME_TEST))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))

        //THEN
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.username", `is`(USERNAME_TEST)))

    }
}