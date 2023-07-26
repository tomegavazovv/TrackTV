package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

class FavoriteCastNotFoundException :
    CustomRuntimeException("Favorite cast for this movie/tvshow was not found.", HttpStatus.NOT_FOUND)
