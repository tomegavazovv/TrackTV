package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class MovieNotFoundException(id: Long) :
    CustomRuntimeException("Movie with id: $id was not found.", HttpStatus.NOT_FOUND)