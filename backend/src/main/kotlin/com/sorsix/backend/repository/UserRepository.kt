package com.sorsix.backend.repository

import com.sorsix.backend.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean

    fun findByEmail(email: String): User?
}