package com.sorsix.backend.exceptions

class UserNotFoundException(id: Long): RuntimeException("User with id $id not found.")