package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class ClubServiceException(
        message: String,
        httpStatus: HttpStatus
) : HaruAttendanceException(message, httpStatus) {

    class ClubNameException(
            name: String
    ) : ClubServiceException(PREFIX_MESSAGE + name, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "그룹의 이름은 30자를 넘길 수 없습니다."
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }
}
