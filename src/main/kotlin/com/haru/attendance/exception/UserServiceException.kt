package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class UserServiceException(
    message: String,
    httpStatus: HttpStatus
) : HaruAttendanceException(message, httpStatus) {

    class UsernameNotFoundException(
        username: String
    ) : UserServiceException(PREFIX_MESSAGE + username, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "해당 유저를 찾을 수 없습니다. 현재 유저의 이름: "
            val httpStatus = HttpStatus.NOT_FOUND
        }
    }

    class UsernameAlreadyExistsException(
        username: String
    ) : UserServiceException(PREFIX_MESSAGE + username, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "해당 유저 이름은 이미 존재합니다. 현재 유저의 이름: "
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }
}