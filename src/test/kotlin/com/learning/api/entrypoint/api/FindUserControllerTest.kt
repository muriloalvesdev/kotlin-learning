package com.learning.api.entrypoint.api

import com.learning.api.BaseControllerUnitTest
import com.learning.api.core.domain.user.User
import com.learning.api.core.usecase.FindUserUseCase
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.given
import org.mockito.kotlin.verify
import org.springframework.http.HttpStatus

class FindUserControllerTest : BaseControllerUnitTest() {

    private val useCase: FindUserUseCase = mock(FindUserUseCase::class.java)
    private val controller: FindUserController = FindUserController(this.useCase)

    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldFind(domain: User) {
        //GIVEN
        given(this.useCase.find(domain.username)).willReturn(domain)

        //WHEN
        val response = this.controller.find(domain.username)

        //THEN
        assertThat(HttpStatus.OK).isEqualTo(response.statusCode)
        assertThat(response.body).isNotNull
        assertThat(domain)
            .usingRecursiveComparison()
            .isEqualTo(response.body)

        verify(this.useCase, times(1))
            .find(domain.username)
    }
}