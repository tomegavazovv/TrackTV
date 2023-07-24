package com.sorsix.backend.service.implementations

import com.sorsix.backend.authentication.service.HashService
import com.sorsix.backend.domain.User
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepo: UserRepository,
    private val hashService: HashService,
) : UserService {
    override fun findById(id: Long): User? = userRepo.findByIdOrNull(id)

    override fun findByUsername(username: String): User? = userRepo.findByName(username)

    override fun findByEmail(email: String): User? = userRepo.findByEmail(email)

    override fun existsByUsername(username: String): Boolean = userRepo.existsByName(username)
    override fun existsByEmail(email: String): Boolean = userRepo.existsByEmail(email)

    override fun registerUser(username: String, password: String, email: String): User =
        userRepo.save(User(username, hashService.hashBcrypt(password), email))
}