package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class CastNotFoundException(castId: Long) : CustomRuntimeException("Cast with id: $castId was not found.", HttpStatus.NOT_FOUND)