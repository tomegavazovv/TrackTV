package com.sorsix.backend.api

import com.sorsix.backend.authentication.service.HashService
import com.sorsix.backend.authentication.service.TokenService
import com.sorsix.backend.dto.LoginDto
import com.sorsix.backend.dto.LoginResponseDto
import com.sorsix.backend.dto.RegisterDto
import com.sorsix.backend.service.interfaces.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
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
    fun login(@Valid @RequestBody payload: LoginDto): ResponseEntity<*> {
        val user = userService.findByEmailOrNull(payload.email)?.takeIf {
            hashService.checkBcrypt(payload.password, it.password)
        }

        return user?.let { ResponseEntity.ok(LoginResponseDto(token = tokenService.createToken(it))) }
            ?: ResponseEntity.badRequest().body("Invalid Credentials.")
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody payload: RegisterDto): ResponseEntity<*> {
        if (userService.existsByUsername(payload.username)) {
            return ResponseEntity.badRequest().body("A user with this username already exists.")
        }

        if (userService.existsByEmail(payload.email)) {
            return ResponseEntity.badRequest().body("A user with this email already exists.")
        }

        val savedUser = userService.registerUser(payload.username, payload.password, payload.email)
        return ResponseEntity.ok(LoginResponseDto(token = tokenService.createToken(savedUser)))
    }
}