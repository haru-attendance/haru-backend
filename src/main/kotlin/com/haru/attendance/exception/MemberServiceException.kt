package com.haru.attendance.exception

import org.springframework.http.HttpStatus

abstract class MemberServiceException(
        message: String,
        httpStatus: HttpStatus
) : HaruAttendanceException(message, httpStatus) {

    class NonMemberException(
            memberId: Long
    ) : MemberServiceException(prefixMessage + memberId, httpStatus) {

        companion object {
            val httpStatus = HttpStatus.UNAUTHORIZED
            val prefixMessage = "멤버의 id가 잘못되었습니다. 현재 memberId: "
        }
    }
}
