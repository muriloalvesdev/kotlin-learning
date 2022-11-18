package com.learning.api.entrypoint.api

import com.learning.api.BaseTest.Companion.USERNAME_TEST
import com.learning.api.core.domain.user.User
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CreateUserControllerIntegrationTest : BaseIntegrationTests() {

    @DisplayName(
        "Deve fazer uma requisicao HTTP na API /users " +
            "com o verbo POST para salvar um usuario na base de dados, " +
            "depois deve buscar com o repository o usuario na base " +
            "para garantir que foi salvo"
    )
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSaveWithSuccess(domain: User) {
        //GIVEN
        this.mockMvc!!.perform(
            post(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper!!.writeValueAsString(domain))
        )

        //WHEN
        val userEntityOptional = this.repository!!.findByUsername(domain.username)

        //THEN
        assertThat(userEntityOptional).isNotEmpty
        assertThat(userEntityOptional.get().username)
            .isEqualTo(USERNAME_TEST)
        assertThat(userEntityOptional.get().uuid).isNotNull
    }
}