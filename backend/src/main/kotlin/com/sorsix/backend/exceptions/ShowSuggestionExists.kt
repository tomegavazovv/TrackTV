package com.sorsix.backend.exceptions

class ShowSuggestionExists(showId: Long, fromId: Long, toId: Long): RuntimeException("Suggestion for show with id $showId from user with id $fromId to user with id $toId already exists.")