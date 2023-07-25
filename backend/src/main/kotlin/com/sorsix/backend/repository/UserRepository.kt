package com.sorsix.backend.repository

import com.sorsix.backend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?

    fun existsByName(name: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User

    @Query("SELECT * FROM tracktv_user WHERE email = :email", nativeQuery = true)
    fun findByEmailOrNull(@Param("email") email: String): User?
}