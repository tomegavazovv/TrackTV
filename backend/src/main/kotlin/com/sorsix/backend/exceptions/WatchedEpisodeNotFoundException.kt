package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class WatchedEpisodeNotFoundException :
    CustomRuntimeException("The selected episode is not in your watched list.", HttpStatus.NOT_FOUND)
