package com.learning.api.entrypoint.api

import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CreateUserControllerIntegrationTest : BaseIntegrationTests() {

    @DisplayName(
        "Deve fazer uma requisicao HTTP na API /users " +
        "com o verbo POST para salvar um usuario na base de dados, " +
        "depois deve buscar com o repository o usuario na base " +
        "para garantir que foi salvo"
    )
    @Test
    fun shouldSaveWithSuccess() {
        //GIVEN
        this.mockMvc!!.perform(
            post("http://localhost:8080/".plus(BASE_URL))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper!!.writeValueAsString(CreateUserController.UserBody(USERNAME_TEST))))

        //WHEN
        val userEntityOptional = this.repository!!.findByUsername(USERNAME_TEST)

        //THEN
        assertThat(userEntityOptional).isNotEmpty
        assertThat(userEntityOptional.get().username())
            .isEqualTo(USERNAME_TEST)
        assertThat(userEntityOptional.get().uuid()).isNotNull
    }
}