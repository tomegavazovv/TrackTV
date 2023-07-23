package com.sorsix.backend.repository.friendship

import com.sorsix.backend.domain.friendship.FriendRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRequestRepository : JpaRepository<FriendRequest, Long>