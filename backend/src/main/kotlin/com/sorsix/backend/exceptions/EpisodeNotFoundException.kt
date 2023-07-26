package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class EpisodeNotFoundException(id: Long) :
    CustomRuntimeException("Episode with id: $id was not found.", HttpStatus.NOT_FOUND)
