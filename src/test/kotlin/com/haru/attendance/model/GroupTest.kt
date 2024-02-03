package com.haru.attendance.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class GroupTest : BehaviorSpec({
    given("이전에 그룹이 없을때 ") {
        `when`("그룹을 생성할 경우") {
            then("이름과 함께 그룹을 생성할 수 있다.") {
                Club("그룹명")
            }
            then("그룹의 이름은 30자를 넘기면 예외를 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    Club("12345678901234567890121234567890123456789012")
                }
            }
        }
    }
})