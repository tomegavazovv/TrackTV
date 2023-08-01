package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class RatingNotFoundException :
    CustomRuntimeException("User has not rated this movie/episode yet.", HttpStatus.NOT_FOUND)