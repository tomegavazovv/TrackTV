package com.sorsix.backend.repository.friendship

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.friendship.FriendRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FriendRequestRepository : JpaRepository<FriendRequest, Long> {

    @Query(
        "SELECT * FROM friend_request fr WHERE fr.sender_id = :senderId " +
                "AND fr.reciever_id = :receiverId " +
                "UNION " +
                "SELECT * FROM friend_request fr WHERE fr.reciever_id = :senderId " +
                "AND fr.sender_id = :receiverId ",
        nativeQuery = true
    )
    fun findFriendRequestByReceiverAndSender(
        @Param("receiverId") receiverId: Long,
        @Param("senderId") senderId: Long
    ): FriendRequest?

    fun findFriendRequestsByRecieverId(user: User): List<FriendRequest>
}