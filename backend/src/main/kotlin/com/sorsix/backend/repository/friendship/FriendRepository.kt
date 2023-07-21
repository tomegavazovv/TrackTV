package com.sorsix.backend.repository.friendship

import com.sorsix.backend.domain.friendship.Friend
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository : JpaRepository<Friend, Long>