package com.sorsix.backend.service

import com.sorsix.backend.domain.User

interface UserService {
    fun findById(id: Long): User?

    fun findByUsername(username: String): User?

    fun findByEmailOrNull(email: String): User?

    fun findByEmail(email: String): User

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun registerUser(username: String, password: String, email: String): User
}