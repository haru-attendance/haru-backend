package com.haru.attendance.exception

import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse

abstract class HaruAttendanceException(
    message: String,
    val httpStatus: HttpStatus
) : RuntimeException(message) {
    fun toErrorResponse(): ErrorResponse {
        return ErrorResponse.create(this, httpStatus, message ?: "")
    }
}
