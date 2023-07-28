package com.sorsix.backend.exceptions

import org.springframework.http.HttpStatus

abstract class CustomRuntimeException(message: String, status: HttpStatus): RuntimeException() {
    val httpStatus = status
    val errorMessage = message

    override val message: String?
        get() = errorMessage

    fun getStatus() = httpStatus
}