package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class WatchedShowNotFoundException :
    CustomRuntimeException("The selected movie is not in your watched list.", HttpStatus.NOT_FOUND)
