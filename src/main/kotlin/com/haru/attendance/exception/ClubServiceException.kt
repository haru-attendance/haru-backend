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

    class NonClubIdException(
            clubId: Long
    ) : ClubServiceException(PREFIX_MESSAGE + clubId, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "전달받은 club ID가 없습니다. 현재 club ID: "
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }

    class DeletedClubException(
            clubId: Long
    ) : ClubServiceException(PREFIX_MESSAGE + clubId, httpStatus) {

        companion object {
            const val PREFIX_MESSAGE = "전달받은 club ID가 이미 삭제되었습니다. 현재 club ID"
            val httpStatus = HttpStatus.BAD_REQUEST
        }
    }
}
