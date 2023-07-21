package com.sorsix.backend.service

import com.sorsix.backend.domain.User
import com.sorsix.backend.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepository,
) {
    fun findById(id: Long): User? {
        return userRepo.findByIdOrNull(id)
    }

    fun findByName(name: String): User? {
        return userRepo.findByName(name)
    }

    fun findByEmail(email: String): User? = userRepo.findByEmail(email)

    fun existsByName(name: String): Boolean {
        return userRepo.existsByName(name)
    }

    fun save(user: User): User {
        return userRepo.save(user)
    }
}