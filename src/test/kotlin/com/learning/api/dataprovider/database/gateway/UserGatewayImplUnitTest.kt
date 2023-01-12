package com.learning.api.dataprovider.database.gateway

import com.learning.api.BaseUnitTest
import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.ConstantsTests.Companion.entity
import com.learning.api.core.domain.user.User
import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.BDDMockito.verify
import org.mockito.Mockito.any
import java.lang.String.format
import java.util.Optional

class UserGatewayImplUnitTest : BaseUnitTest() {

    private val gatewayImpl: UserGateway = UserGatewayImpl(this.repository, this.mapper)

    @DisplayName(
        "Deve tentar salvar o usuario, mas ele " +
            "ja existe na base de dados e deve retornar UserAlreadyExistsException."
    )
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldTrySaveButReturnError(domain: User) {
        //GIVEN
        val messageErrorExpected = format(
            "username=[%s] already exists.", USERNAME_TEST
        )
        given(
            this.repository.existsByUsername(USERNAME_TEST)
        ).willReturn(true)

        //WHEN
        val exception = assertThrows<UserAlreadyExistsException> {
            this.gatewayImpl.save(domain)
        }

        //THEN
        assertThat(exception)
            .isInstanceOf(UserAlreadyExistsException::class.java)
        assertThat(exception.message)
            .isEqualTo(messageErrorExpected)
    }

    @DisplayName("Deve salvar o usuario com sucesso.")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldSaveWithSuccess(domain: User) {
        //GIVEN
        given(
            this.repository.existsByUsername(USERNAME_TEST)
        ).willReturn(false)

        given(
            this.repository.save(
                any(
                    UserEntity::class.java
                )
            )
        ).willReturn(
            entity
        )

        //WHEN
        this.gatewayImpl.save(domain)

        //THEN
        verify(this.repository, times(1))
            .existsByUsername(USERNAME_TEST)
        verify(this.repository, times(1))
            .save(
                any(
                    UserEntity::class.java
                )
            )
    }

    @DisplayName("Deve buscar usuario por username e deve encontra-lo.")
    @ParameterizedTest
    @ArgumentsSource(UserEntityProviderTests::class)
    fun shouldFindWithExists(
        userEntity: UserEntity
    ) {
        //GIVEN
        given(
            this.repository.findByUsername(USERNAME_TEST)
        ).willReturn(Optional.of(userEntity))

        //WHEN
        val userEntityOptional = this.gatewayImpl.find(USERNAME_TEST)

        //THEN
        verify(this.repository, times(1))
            .findByUsername(USERNAME_TEST)

        assertThat(userEntityOptional).isNotEmpty

        assertThat(userEntity.username)
            .isEqualTo(userEntityOptional.get().username)
    }

    @DisplayName("Deve buscar usuario por username e nao deve encontra-lo.")
    @Test
    fun shouldFindWithNotExists() {
        //GIVEN
        given(
            this.repository.findByUsername(USERNAME_TEST)
        ).willReturn(Optional.empty())

        //WHEN
        val userEntityOptional = this.gatewayImpl.find(USERNAME_TEST)

        //THEN
        verify(this.repository, times(1))
            .findByUsername(USERNAME_TEST)

        assertThat(userEntityOptional).isEmpty
    }
}