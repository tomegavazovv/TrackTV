package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.friendship.Friend
import com.sorsix.backend.domain.friendship.FriendRequest
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.service.FriendRequestService
import org.springframework.stereotype.Service

@Service
class FriendRequestServiceImpl(
    val userRepository: UserRepository,
    val friendRequestRepository: FriendRequestRepository,
    val friendRepository: FriendRepository
): FriendRequestService {
    override fun findFriendRequestsByRecieverId(id: Long) =
        friendRequestRepository.findFriendRequestsByRecieverId(userRepository.findById(id).get())

    override fun acceptRequest(fromId: Long, toId: Long) {
        friendRepository.save(Friend(user = userRepository.findById(fromId).get(),
            friend = userRepository.findById(toId).get()))

        friendRequestRepository.deleteById(friendRequestRepository
            .findFriendRequestByReceiverAndSender(fromId, toId)!!.id)
    }

    override fun declineRequest(fromId: Long, toId: Long) {
        friendRequestRepository.deleteById(friendRequestRepository
            .findFriendRequestByReceiverAndSender(fromId, toId)!!.id)
    }
}