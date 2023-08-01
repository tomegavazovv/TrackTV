package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class ShowNotFoundException(id: Long) :
    CustomRuntimeException("Show with id: $id was not found.", HttpStatus.NOT_FOUND)