package com.sorsix.backend.repository

import com.sorsix.backend.domain.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): User?

    fun existsByName(name: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User

    @Query(
        "SELECT u.id, u.username, u.email, u.password FROM tracktv_user u  " +
                "JOIN (SELECT f.user_id FROM friends f WHERE f.user_id = :profileId " +
                "UNION " +
                "SELECT f.friend_id FROM friends f WHERE f.friend_id = :profileId " +
                ") ids ON u.id = ids.user_id ",
        nativeQuery = true)
    fun findFriendsByProfileId(profileId: Long): List<User>

    @Query("SELECT * from tracktv_user WHERE email = :email", nativeQuery = true)
    fun findByEmailOrNull(email: String?): User?
}