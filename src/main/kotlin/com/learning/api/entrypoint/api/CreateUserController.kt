package com.learning.api.entrypoint.api

import com.learning.api.core.domain.user.User
import com.learning.api.core.usecase.CreateUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("users")
class CreateUserController(
    private val useCase: CreateUserUseCase
) {

    @PostMapping
    fun save(
        @RequestBody domain: User
    ): ResponseEntity<Any> {
        this.useCase.save(domain)
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("users/{username}")
                .buildAndExpand(domain.username)
                .toUri()
        ).build<Any>()
    }
}