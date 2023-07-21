package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserWatchShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserWatchShowRepository : JpaRepository<UserWatchShow, Long>