package com.learning.api.providers

import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.core.domain.user.User
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class UserProviderTests : ArgumentsProvider {
    override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(User(USERNAME_TEST)).map(Arguments::of)
    }
}