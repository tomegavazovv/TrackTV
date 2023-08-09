package com.sorsix.backend.repository.friendship

import com.sorsix.backend.domain.friendship.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository : JpaRepository<Friend, Long> {

    @Query(
        "SELECT * FROM friends f where f.user_id = :userId AND f.friend_id = :friendId " +
                "UNION " +
                "SELECT * FROM friends f where f.user_id = :friendId AND f.friend_id = :userId",
        nativeQuery = true
    )
    fun checkFriendship(@Param("userId") userId: Long,
                        @Param("friendId") friendId: Long): List<Friend>
    @Modifying
    @Query(
        "DELETE FROM Friend f WHERE (f.user.id = :userId AND f.friend.id = :friendId) " +
                "OR (f.user.id = :friendId AND f.friend.id = :userId)"
    )
    fun deleteFriend(@Param("userId") userId: Long, @Param("friendId") friendId: Long): Int

    fun findAllById(id: Long): List<Friend>
}