package com.learning.api.entrypoint.api

import com.learning.api.BaseControllerUnitTest
import com.learning.api.core.domain.user.User
import com.learning.api.core.usecase.CreateUserUseCase
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.http.HttpStatus

class CreateUserControllerTest : BaseControllerUnitTest() {
    private val usecase: CreateUserUseCase = mock(CreateUserUseCase::class.java)
    private val controller: CreateUserController = CreateUserController(usecase)

    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSave(domain: User) {
        //GIVEN
        doNothing().`when`(this.usecase).save(domain)

        //WHEN
        val response = this.controller.save(domain)

        //THEN
        assertThat(HttpStatus.CREATED).isEqualTo(response.statusCode)
        assertThat(response.headers.location).isNotNull
        assertThat("http://localhost/users/muriloalvesdev")
            .isEqualTo(response.headers.location.toString())

        verify(this.usecase, times(1)).save(domain)
    }
}