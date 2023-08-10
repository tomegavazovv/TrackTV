package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class SeasonNotFoundException(id: Number) :
    CustomRuntimeException("Season number $id was not found.", HttpStatus.NOT_FOUND)