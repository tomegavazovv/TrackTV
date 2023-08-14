package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.friendship.Friend
import com.sorsix.backend.domain.friendship.FriendRequest
import com.sorsix.backend.exceptions.FriendRequestNotFoundException
import com.sorsix.backend.exceptions.UserNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.service.interfaces.FriendRequestService
import org.springframework.stereotype.Service

@Service
class FriendRequestServiceImpl(
    val userRepository: UserRepository,
    val friendRequestRepository: FriendRequestRepository,
    val friendRepository: FriendRepository
) : FriendRequestService {
    override fun findFriendRequestsByReceiverId(id: Long): List<FriendRequest> {
        val receiver: User =
            userRepository.findByIdOrNull(id) ?: throw UserNotFoundException("Receiver with id $id not found.")
        return friendRequestRepository.findFriendRequestsByReceiverId(receiver)
    }

    override fun acceptRequest(fromId: Long, toId: Long) {
        val friendRequest: FriendRequest = friendRequestRepository.findFriendRequestByReceiverAndSender(fromId, toId)
            ?: throw FriendRequestNotFoundException("There is no friend request between users with id $fromId and $toId")
        val fromUser =
            userRepository.findByIdOrNull(fromId) ?: throw UserNotFoundException("User with id $fromId not found.")
        val toUser =
            userRepository.findByIdOrNull(toId) ?: throw UserNotFoundException(("User with id $fromId not found."))

        friendRepository.save(
            Friend(
                user = fromUser, friend = toUser
            )
        )

        friendRequestRepository.deleteById(friendRequest.id)
    }

    override fun declineRequest(fromId: Long, toId: Long) {
        val friendRequest: FriendRequest = friendRequestRepository.findFriendRequestByReceiverAndSender(fromId, toId)
            ?: throw FriendRequestNotFoundException("There is no friend request between users with id $fromId and $toId.")

        friendRequestRepository.deleteById(friendRequest.id)
    }
}