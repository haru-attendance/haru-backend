package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class MemberServiceException(
        message: String,
        httpStatus: HttpStatus
) : HaruAttendanceException(message, httpStatus) {

    class NonMemberException(
            memberId: Long
    ) : MemberServiceException(PREFIX_MESSAGE + memberId, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "멤버의 ID가 잘못되었습니다. 현재 멤버의 ID: "
            val httpStatus = HttpStatus.UNAUTHORIZED
        }
    }

    class MemberNameException(
            name: String
    ) : MemberServiceException(PREFIX_MESSAGE + name, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "멤버의 이름은 30자를 넘길 수 없습니다. 현재 멤버 이름: "
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }
}
