package com.learning.api.dataprovider.database.entity.mapper

import com.learning.api.BaseTest
import com.learning.api.core.domain.user.User
import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.providers.UserEntityProviderTests
import com.learning.api.providers.UserProviderTests
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class UserMapperTest : BaseTest() {

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