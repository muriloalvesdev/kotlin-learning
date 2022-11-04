package com.learning.api.entrypoint.advice

import com.learning.api.dataprovider.database.exception.UserAlreadyExistsException
import com.learning.api.dataprovider.database.exception.UsernameNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdviceController : ResponseEntityExceptionHandler() {
    companion object {
        private val NOT_FOUND: HttpStatus = HttpStatus.NOT_FOUND
        private val CONFLICT: HttpStatus = HttpStatus.CONFLICT
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handler(
        usernameNotFoundException: UsernameNotFoundException
    ): ResponseEntity<ResponseException> {
        return ResponseEntity
            .status(NOT_FOUND)
            .body(
                ResponseException(usernameNotFoundException.message!!, NOT_FOUND.value())
            )
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handler(
        userAlreadyExistsException: UserAlreadyExistsException
    ): ResponseEntity<ResponseException> {
        return ResponseEntity
            .status(NOT_FOUND)
            .body(
                ResponseException(userAlreadyExistsException.message!!, CONFLICT.value())
            )
    }

    data class ResponseException(
        val message: String,
        val code: Int
    )
}