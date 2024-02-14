package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class UnitServiceException(
        message: String,
        httpStatus: HttpStatus
) : HaruAttendanceException(message, httpStatus) {

    class UnitNameException(
            name: String
    ) : UnitServiceException(PREFIX_MESSAGE + name, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "유닛의 이름은 10자를 넘기면 안됩니다. 현재 유닛의 이름: "
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }
}
