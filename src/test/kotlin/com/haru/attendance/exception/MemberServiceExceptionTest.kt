package com.haru.attendance.exception

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.http.HttpStatus

class MemberServiceExceptionTest : BehaviorSpec({
    given("멤버 서비스 내에서 존재하지 않는 멤버를 조회할 때") {
        `when`("전달받은 멤버의 id를 전달하면") {
            val memberCustomException = MemberServiceException.NonMemberException(1L)
            then("멤버가 존재하지 않는다는 커스텀 예외를 생성할 수 있다.") {
                memberCustomException.httpStatus shouldBeEqual HttpStatus.UNAUTHORIZED
            }
        }
    }
})
