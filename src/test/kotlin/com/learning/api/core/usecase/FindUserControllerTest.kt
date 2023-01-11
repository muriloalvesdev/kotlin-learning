package com.learning.api.core.usecase

import com.learning.api.BaseTest
import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.BDDMockito
import java.util.Optional

class FindUserControllerTest : BaseTest() {
    private val useCase: FindUserUseCase = FindUserUseCase(this.gateway)

    @DisplayName("Deve buscar um usuario pelo username e encontra-lo.")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldFindWithSuccess(
        domain: User
    ) {
        //GIVEN
        BDDMockito.given(
            this.gateway.find(USERNAME_TEST)
        ).willReturn(Optional.of(domain))

        //WHEN
        val user = this.useCase.find(USERNAME_TEST)

        //THEN
        BDDMockito.verify(this.gateway, BDDMockito.times(1))
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
            String.format("username=[%s] not found.", USERNAME_TEST)

        BDDMockito.given(
            this.gateway.find(USERNAME_TEST)
        ).willReturn(Optional.empty())

        //WHEN
        val exception = assertThrows<UsernameNotFoundException> {
            this.useCase.find(USERNAME_TEST)
        }

        //THEN
        BDDMockito.verify(this.gateway, BDDMockito.times(1))
            .find(USERNAME_TEST)

        assertThat(exception)
            .isInstanceOf(UsernameNotFoundException::class.java)
        assertThat(exception.message).isEqualTo(messageErrorExpected)
    }
}