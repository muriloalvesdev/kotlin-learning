package com.learning.api.entrypoint.api

import com.learning.api.BaseIntegrationTests
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.given
import org.springframework.http.MediaType
import java.util.Optional

class FindUserControllerIntegrationTest : BaseIntegrationTests() {

    @DisplayName("Deve buscar o usuário pelo username.")
    @ParameterizedTest
    @ArgumentsSource(UserEntityProviderTests::class)
    fun shouldFind(entity: UserEntity) {
        //GIVEN
        val username = entity.username
        val domain = this.mapper.toUserDomain(entity)
        val expectedResponse = this.objectMapper.writeValueAsString(domain)

        given(gateway.find(username)).willReturn(Optional.ofNullable(domain))

        //WHEN
        val response = requestGet(username)

        //THEN
        val responseString = response
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { json(expectedResponse) }
            }.andReturn()
            .response
            .contentAsString

        val domainResponse = this.objectMapper.readValue(responseString, User::class.java)

        assertThat(entity)
            .usingRecursiveComparison()
            .ignoringFields("uuid")
            .isEqualTo(this.mapper.toUserEntity(domainResponse))
        verify(gateway, times(1))
            .find(anyString())
    }

    @DisplayName("Deve tentar buscar o usuário pelo username e retornar not found.")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldTryFindUserByUsernameButReturnNotFound(domain: User) {
        //GIVEN
        val username = domain.username
        given(gateway.find(username)).willReturn(Optional.empty())

        //WHEN
        val response = requestGet(username)

        //THEN
        response.andExpect {
            status { isNotFound() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content {
                json(
                    "{\n" +
                        "    \"message\": \"username=[$username] not found.\",\n" +
                        "    \"code\": 404\n" +
                        "}"
                )
            }
        }

        verify(gateway, times(1))
            .find(anyString())
    }
}