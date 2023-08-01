package com.sorsix.backend.repository

import com.sorsix.backend.domain.Cast
import org.springframework.data.repository.CrudRepository

interface CastRepository : CrudRepository<Cast, Long>