package com.sorsix.backend.controller

import com.sorsix.backend.domain.User
import com.sorsix.backend.dto.ApiException
import com.sorsix.backend.dto.LoginDto
import com.sorsix.backend.dto.LoginResponseDto
import com.sorsix.backend.dto.RegisterDto
import com.sorsix.backend.service.HashService
import com.sorsix.backend.service.TokenService
import com.sorsix.backend.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByEmail(payload.email) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(400, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.existsByName(payload.username)) {
            throw ApiException(400, "Name already exists")
        }

        val user = User(payload.username, hashService.hashBcrypt(payload.password), payload.email)
        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}