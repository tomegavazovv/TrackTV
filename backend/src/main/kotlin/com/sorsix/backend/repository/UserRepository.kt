package com.sorsix.backend.repository

import com.sorsix.backend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import com.sorsix.backend.domain.movie.Movie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User

    @Query("SELECT u from User u where u.name = :name")
    fun findByNameOrNull(name: String): User?

    fun findAllByNameContainingAndEmailNot(searchingFor: String, ownEmail: String): List<User>
    fun searchByNameContainingIgnoreCase(name: String): List<User>

    fun existsByName(name: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User

    @Query("SELECT u from User u WHERE u.id = :id")
    fun findByIdOrNull(id: Long) : User?

    @Query(
        "SELECT u.id, u.username, u.email, u.password FROM tracktv_user u  " +
                "JOIN (SELECT f.friend_id FROM friends f WHERE f.user_id = :profileId " +
                "UNION " +
                "SELECT f.user_id FROM friends f WHERE f.friend_id = :profileId " +
                ") ids ON u.id = ids.friend_id ",
        nativeQuery = true)
    fun findFriendsByProfileId(profileId: Long): List<User>

    @Query("SELECT * from tracktv_user WHERE email = :email", nativeQuery = true)
    fun findByEmailOrNull(email: String?): User?

}