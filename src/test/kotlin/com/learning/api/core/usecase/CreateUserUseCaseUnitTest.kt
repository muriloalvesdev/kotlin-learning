package com.learning.api.core.usecase

import com.learning.api.BaseUnitTest
import com.learning.api.core.domain.user.User
import com.learning.api.providers.UserProviderTests
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.BDDMockito.doNothing
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify

class CreateUserUseCaseUnitTest : BaseUnitTest() {

    private val useCase: CreateUserUseCase = CreateUserUseCase(this.gateway)

    @DisplayName("Deve savar o usuario.")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSave(domain: User) {
        //GIVEN
        doNothing()
            .`when`(this.gateway)
            .save(domain)

        //WHEN
        this.useCase.save(domain)

        //THEN
        verify(this.gateway, times(1))
            .save(domain)
    }

}