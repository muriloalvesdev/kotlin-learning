package com.learning.api

import org.junit.jupiter.api.BeforeEach
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

open class BaseControllerUnitTest : BaseUnitTest() {
    @BeforeEach
    fun setUp() {
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
    }
}