package com.learning.api.dataprovider.database.entity.mapper

import com.learning.api.BaseUnitTest
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class UserMapperUnitTest : BaseUnitTest() {

    @DisplayName("Deve converter user_domain para user_entity")
    @ParameterizedTest
    @ArgumentsSource(UserProviderTests::class)
    fun shouldConvertDomainToEntity(domain: User) {
        //GIVEN is param

        //WHEN
        val entity = this.mapper.toUserEntity(domain)

        //THEN
        assertThat(entity.username)
            .isEqualTo(domain.username)
    }

    @DisplayName("Deve converter user_entity para user_domain")
    @ParameterizedTest
    @ArgumentsSource(UserEntityProviderTests::class)
    fun shouldConvertEntityToDomain(entity: UserEntity) {
        //GIVEN is param

        //WHEN
        val domain = this.mapper.toUserDomain(entity)

        //THEN
        assertThat(domain.username)
            .isEqualTo(entity.username)
    }

}