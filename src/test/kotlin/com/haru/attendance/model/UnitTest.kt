package com.haru.attendance.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UnitTest : BehaviorSpec({
    given("이전에 유닛이 없을 경우") {
        `when`("유닛을 생성할 때") {
            then("유닛 이름과 클럽 아이디로 유닛을 생성한다.") {
                val unit = Unit("테스트", 1)
                unit.clubId shouldBe 1
                unit.name shouldBe "테스트"
            }
            then("유닛의 이름은 10자를 넘기면 예외를 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    Unit("12345678901", 1)
                }
            }
        }
    }
    given("유닛") {
        `when`("같은 이름과 clubId를 가진 유닛은") {
            then("같은 유닛이다") {
                val unit1 = Unit("테스트", 1)
                val unit2 = Unit("테스트", 1)
                unit1 shouldBe unit2
            }
        }
    }
})