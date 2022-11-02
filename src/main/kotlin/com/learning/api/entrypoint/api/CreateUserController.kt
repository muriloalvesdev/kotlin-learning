package com.learning.api.entrypoint.api

import com.learning.api.core.usecase.UserUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class CreateUserController(
    private val useCase: UserUseCase
) {

    companion object {
        private val CREATED: HttpStatus = HttpStatus.CREATED
    }

    @PostMapping
    fun save(@RequestBody body: UserBody): ResponseEntity<Any> {
        this.useCase.save(body.username())
        return ResponseEntity.status(CREATED).build<Any>()
    }

    data class UserBody(
        val username: String
    ) {
        fun username(): String {
            return this.username
        }
    }
}