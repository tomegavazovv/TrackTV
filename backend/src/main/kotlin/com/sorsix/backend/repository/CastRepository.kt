package com.sorsix.backend.repository

import com.sorsix.backend.domain.Cast
import org.springframework.data.jpa.repository.JpaRepository

interface CastRepository : JpaRepository<Cast, Long>