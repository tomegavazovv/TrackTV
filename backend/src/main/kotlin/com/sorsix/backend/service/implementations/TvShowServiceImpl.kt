package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.service.TvShowService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TvShowServiceImpl(private val showRepository: ShowRepository) : TvShowService {
    override fun getById(id: Long): Show = this.showRepository.findByIdOrNull(id) ?: throw ShowNotFoundException(id)

}