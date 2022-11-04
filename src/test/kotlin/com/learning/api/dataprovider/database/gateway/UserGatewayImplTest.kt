package com.learning.api.dataprovider.database.gateway

import com.learning.api.core.gateway.UserGateway
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.repository.UserEntityRepository
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
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
import org.mockito.Mockito.mock
import java.lang.String.format
import java.util.Optional

class UserGatewayImplTest {
    private val repository: UserEntityRepository = mock(UserEntityRepository::class.java)
    private val gateway: UserGateway = UserGatewayImpl(this.repository)

    @DisplayName(
        "Deve tentar salvar o usuario, mas ele " +
            "ja existe na base de dados e deve retornar UserAlreadyExistsException."
    )
    @Test
    fun shouldTrySaveButReturnError() {
        //GIVEN
        val messageErrorExpected = format(
            "username=[%s] already exists.", USERNAME_TEST
        )
        given(
            this.repository.existsByUsername(USERNAME_TEST)
        ).willReturn(true)

        //WHEN
        val exception = assertThrows<UserAlreadyExistsException> {
            this.gateway.save(USERNAME_TEST)
        }

        //THEN
        assertThat(exception)
            .isInstanceOf(UserAlreadyExistsException::class.java)
        assertThat(exception.message)
            .isEqualTo(messageErrorExpected)
    }

    @DisplayName("Deve salvar o usuario com sucesso.")
    @Test
    fun shouldSaveWithSuccess() {
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
            UserEntity(USERNAME_TEST)
        )

        //WHEN
        this.gateway.save(USERNAME_TEST)

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
        val userEntityOptional = this.gateway.find(USERNAME_TEST)

        //THEN
        verify(this.repository, times(1))
            .findByUsername(USERNAME_TEST)

        assertThat(userEntityOptional).isNotEmpty

        assertThat(userEntity.username())
            .isEqualTo(userEntityOptional.get().username())
    }

    @DisplayName("Deve buscar usuario por username e nao deve encontra-lo.")
    @Test
    fun shouldFindWithNotExists() {
        //GIVEN
        given(
            this.repository.findByUsername(USERNAME_TEST)
        ).willReturn(Optional.empty())

        //WHEN
        val userEntityOptional = this.gateway.find(USERNAME_TEST)

        //THEN
        verify(this.repository, times(1))
            .findByUsername(USERNAME_TEST)

        assertThat(userEntityOptional).isEmpty
    }
}