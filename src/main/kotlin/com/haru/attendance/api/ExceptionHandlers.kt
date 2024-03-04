package com.haru.attendance.api

import com.haru.attendance.exception.HaruAttendanceException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(HaruAttendanceException::class)
    fun handleHaruAttendanceException(e: HaruAttendanceException) = e.toErrorResponse()
}