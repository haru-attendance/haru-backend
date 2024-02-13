package com.haru.attendance.model

import com.haru.attendance.exception.ClubServiceException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ClubTest : BehaviorSpec({
    given("이전에 그룹이 없을때 ") {
        `when`("그룹을 생성할 경우") {
            then("이름과 함께 그룹을 생성할 수 있다.") {
                val club = Club("그룹명")
                club.name shouldBe "그룹명"
            }
            then("그룹의 이름은 30자를 넘기면 예외를 발생한다.") {
                shouldThrow<ClubServiceException.ClubNameException> {
                    Club("12345678901234567890121234567890123456789012")
                }
            }
        }
    }
    given("이전에 그룹이 있을때") {
        `when`("그룹의 이름을 수정할 경우") {
            then("그룹의 이름을 수정할 수 있다.") {
                val club = Club("그룹명")
                club.updateName("그룹명2")
                club.name shouldBe "그룹명2"
            }
            then("그룹의 이름은 30자를 넘기면 예외를 발생한다.") {
                val club = Club("그룹명")
                shouldThrow<ClubServiceException.ClubNameException> {
                    club.updateName("12345678901234567890121234567890123456789012")
                }
            }
        }
    }
})
