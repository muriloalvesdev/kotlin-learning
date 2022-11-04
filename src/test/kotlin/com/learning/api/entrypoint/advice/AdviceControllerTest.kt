package com.learning.api.entrypoint.advice

import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import com.learning.api.utils.ConstantsTests.Companion.USERNAME_TEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.lang.String.format

class AdviceControllerTest {

    private val adviceController: AdviceController = AdviceController()

    @DisplayName(
        "Deve retornar a exception UsernameNotFoundException " +
        "e confirmar o response status HttpStatus.NOT_FOUND"
    )
    @Test
    fun shouldReturnUsernameNotFoundException() {
        //GIVEN
        val messageExceptionExpected = format("username=[%s] not found.", USERNAME_TEST)

        //WHEN
        val responseEntity = this.adviceController
            .handler(UsernameNotFoundException(USERNAME_TEST))

        //THEN
        assertThat(responseEntity.body!!.code)
            .isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(responseEntity.body!!.message)
            .isEqualTo(messageExceptionExpected)
    }

    @DisplayName(
        "Deve retornar a exception UserAlreadyExistsException " +
        "e confirmar o response status HttpStatus.CONFLICT. "
    )
    @Test
    fun shouldReturnUserAlreadyExistsException() {
        //GIVEN
        val messageExceptionExpected = format("username=[%s] already exists.", USERNAME_TEST)

        //WHEN
        val responseEntity = this.adviceController
            .handler(UserAlreadyExistsException(USERNAME_TEST))

        //THEN
        assertThat(responseEntity.body!!.code)
            .isEqualTo(HttpStatus.CONFLICT.value())
        assertThat(responseEntity.body!!.message)
            .isEqualTo(messageExceptionExpected)
    }
}