package com.learning.api.entrypoint.api

import com.learning.api.BaseIntegrationTests
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.providers.UserProviderTests
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.kotlin.doThrow
import org.springframework.http.MediaType

class CreateUserControllerIntegrationTest : BaseIntegrationTests() {

    @DisplayName("Deve salvar o usuário")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSave(domain: User) {
        //GIVEN | WHEN
        val response = requestPost(domain)

        //THEN
        response.andExpect {
            status { isCreated() }
            header { exists("Location") }
        }
    }

    @DisplayName("Deve tentar salvar um usuário que já existe e receber mensagem de erro como resposta")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldTrySaveUserAlreadyExistsAndResponseError(domain: User) {
        //GIVEN
        val username = domain.username
        doThrow(UserAlreadyExistsException(username)).`when`(gateway).save(domain)

        //WHEN
        val response = requestPost(domain)

        //THEN
        response.andExpect {
            status { isConflict() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    "{\n" +
                        "    \"message\": \"username=[$username] already exists.\",\n" +
                        "    \"code\": 409\n" +
                        "}"
                )
            }
        }
    }
}