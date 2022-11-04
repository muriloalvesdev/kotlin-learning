package com.learning.api.providers

import com.learning.api.dataprovider.database.entity.UserEntity
import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class UserEntityProviderTests : ArgumentsProvider {
    override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(
            UserEntity(USERNAME_TEST)
        ).map(Arguments::of)
    }
}