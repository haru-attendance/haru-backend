package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class HaruAttendanceException(
        message: String,
        val httpStatus: HttpStatus
) : RuntimeException(message) {

}
