package com.sorsix.backend.service

import com.sorsix.backend.domain.UserSecurity
import com.sorsix.backend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JpaUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? =
        userRepository.findByEmail(username)?.let { UserSecurity(it) }

}
