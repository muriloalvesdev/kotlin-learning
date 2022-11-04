package com.learning.api.core.usecase

import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.mock
import java.lang.String.format
import java.util.Optional

class UserUseCaseTest {

    private val gateway: UserGateway = mock(UserGateway::class.java)
    private val useCase: UserUseCase = UserUseCase(this.gateway)

    @DisplayName("Deve savar o usuario.")
    @Test
    fun shouldSave() {
        //GIVEN
        doNothing()
            .`when`(this.gateway)
            .save(USERNAME_TEST)

        //WHEN
        this.useCase.save(USERNAME_TEST)

        //THEN
        verify(this.gateway, times(1))
            .save(USERNAME_TEST)
    }

    @DisplayName("Deve buscar um usuario pelo username e encontra-lo.")
    @ParameterizedTest
    @ArgumentsSource(UserEntityProviderTests::class)
    fun shouldFindWithSuccess(
        userEntity: UserEntity
    ) {
        //GIVEN
        given(
            this.gateway.find(USERNAME_TEST)
        ).willReturn(Optional.of(userEntity))

        //WHEN
        val user = this.useCase.find(USERNAME_TEST)

        //THEN
        verify(this.gateway, times(1))
            .find(USERNAME_TEST)

        assertThat(user.username).isEqualTo(USERNAME_TEST)
    }

    @DisplayName(
        "Deve tentar buscar o usuario pelo username, " +
            "mas nao deve encontra-lo e deve lancar UsernameNotFoundException."
    )
    @Test
    fun shouldTryFindWithError() {
        //GIVEN
        val messageErrorExpected =
            format("username=[%s] not found.", USERNAME_TEST)

        given(
            this.gateway.find(USERNAME_TEST)
        ).willReturn(Optional.empty())

        //WHEN
        val exception = assertThrows<UsernameNotFoundException> {
            this.useCase.find(USERNAME_TEST)
        }

        //THEN
        verify(this.gateway, times(1))
            .find(USERNAME_TEST)

        assertThat(exception)
            .isInstanceOf(UsernameNotFoundException::class.java)
        assertThat(exception.message).isEqualTo(messageErrorExpected)
    }
}