package com.learning.api.entrypoint.api

import com.learning.api.core.usecase.UserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class FindUserController(
    private val useCase: UserUseCase
) {

    @GetMapping("{username}")
    fun find(
        @PathVariable(name = "username")
        username: String
    ) = this.useCase.find(username)
}