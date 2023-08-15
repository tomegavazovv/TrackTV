package com.sorsix.backend.exceptions

class MovieSuggestionExists(movieId: Long, fromId: Long, toId: Long): RuntimeException("Suggestion for movie with id $movieId from user with id $fromId to user with id $toId already exists.")