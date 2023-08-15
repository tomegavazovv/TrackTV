package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.friendship.FriendRequest
import com.sorsix.backend.exceptions.*
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.service.interfaces.FriendService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FriendServiceImpl(
    val friendRepository: FriendRepository,
    val friendRequestRepository: FriendRequestRepository,
    val userRepository: UserRepository
) : FriendService {
    override fun getFriends(userId: Long): List<User> {
        val result: List<User> = userRepository.findFriendsByProfileId(userId)
        if (result.isEmpty()) {
            throw FriendNotFoundException()
        }
        return result
    }

    override fun addFriend(fromId: Long, toId: Long): FriendRequest {

        val sender: User = userRepository.findByIdOrNull(fromId) ?: throw UserNotFoundException(fromId)
        val receiver: User = userRepository.findByIdOrNull(toId) ?: throw UserNotFoundException(toId)

        return if (friendRepository.checkFriendship(fromId, toId).isEmpty()) {
            if (friendRequestRepository.findFriendRequestByReceiverAndSender(fromId, toId) == null) {
                friendRequestRepository.save(FriendRequest(senderId = sender, receiverId = receiver))
            } else throw FriendRequestExistsException(fromId, toId)

        } else throw AlreadyFriendsException()

    }

    @Transactional
    override fun removeFriend(userId: Long, friendId: Long) {
        val friendship = friendRepository.checkFriendship(userId, friendId)
        if (friendship.isNotEmpty()) {
            friendRepository.deleteFriend(userId, friendId)
        } else {
            throw FriendshipNotFoundException(userId, friendId)
        }
    }

    override fun getCleanUsers(searchingFor: String, userSearchingId: Long): List<User> {
        val user = userRepository.findById(userSearchingId).get()
        val users = userRepository.findAllByNameContainingAndEmailNot(searchingFor, user.email)
        val requests = friendRequestRepository.findFriendRequestsBySenderIdOrReceiverId(user, user).map { friendRequest ->
            if (friendRequest.senderId.id == user.id) {
                friendRequest.receiverId
            } else {
                friendRequest.senderId
            }
        }
        val friends = friendRepository.findAllById(user.id!!).map { friend ->
            if (friend.user.id == user.id!!) {
                friend.friend
            } else {
                friend.user
            }
        }
        val filteredUsers = users.filter {
            !friends.contains(it) && !requests.contains(it)
        }
        return filteredUsers
    }


}