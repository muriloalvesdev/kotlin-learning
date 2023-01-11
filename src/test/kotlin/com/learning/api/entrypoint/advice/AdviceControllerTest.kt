package com.learning.api.entrypoint.advice

import com.learning.api.BaseTest
import com.learning.api.ConstantsTests.Companion.USERNAME_TEST
import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class AdviceControllerTest : BaseTest() {

    private val adviceController: AdviceController = AdviceController()

    @DisplayName(
        "Deve retornar a exception UsernameNotFoundException " +
        "e confirmar o response status HttpStatus.NOT_FOUND"
    )
    @Test
    fun shouldReturnUsernameNotFoundException() {
        //GIVEN
        val exceptionExpected = UsernameNotFoundException(USERNAME_TEST)

        //WHEN
        val responseEntity = this.adviceController
            .handler(exceptionExpected)

        //THEN
        assertThat(responseEntity.body!!.code)
            .isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(responseEntity.body!!.message)
            .isEqualTo(exceptionExpected.message)
    }

    @DisplayName(
        "Deve retornar a exception UserAlreadyExistsException " +
        "e confirmar o response status HttpStatus.CONFLICT. "
    )
    @Test
    fun shouldReturnUserAlreadyExistsException() {
        //GIVEN
        val exceptionExpected = UserAlreadyExistsException(USERNAME_TEST)
        //WHEN
        val responseEntity = this.adviceController
            .handler(exceptionExpected)

        //THEN
        assertThat(responseEntity.body).isNotNull
        assertThat(responseEntity.body!!.code)
            .isEqualTo(HttpStatus.CONFLICT.value())
        assertThat(responseEntity.body!!.message.contains(exceptionExpected.username))
            .isTrue
        assertThat(responseEntity.body!!.message)
            .isEqualTo(exceptionExpected.message)
    }
}