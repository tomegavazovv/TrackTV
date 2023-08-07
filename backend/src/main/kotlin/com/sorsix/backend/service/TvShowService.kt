package com.sorsix.backend.service

import com.sorsix.backend.domain.show.Show

interface TvShowService {

    fun getById(id: Long): Show
}