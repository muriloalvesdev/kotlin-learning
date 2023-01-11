package com.learning.api.providers

import com.learning.api.ConstantsTests.Companion.entity
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class UserEntityProviderTests : ArgumentsProvider {
    override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> {
        return Stream.of(entity).map(Arguments::of)
    }
}