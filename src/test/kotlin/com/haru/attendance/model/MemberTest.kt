package com.haru.attendance.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MemberTest : BehaviorSpec(
    {
        given("이전에 멤버가 없을 경우") {
            `when`("멤버를 생성할 때") {
                then("clubId, unit, 멤버 이름으로 멤버를 생성한다.") {
                    val unit = Unit("테스트", 1)
                    val member = Member(1, "echo", unit)
                    member.clubId shouldBe 1
                    member.unit shouldBe unit
                    member.name shouldBe "echo"
                }
                then("멤버의 이름은 30자를 넘기면 예외를 발생한다.") {
                    val unit = Unit("테스트", 1)
                    shouldThrow<IllegalArgumentException> {
                        Member(1, "12345678901234567890121234567890123456789012", unit)
                    }
                }
            }
        }
        given("이전에 멤버가 있을 경우") {
            `when`("멤버의 이름을 수정할 때") {
                then("멤버의 이름을 수정할 수 있다.") {
                    val unit = Unit("테스트", 1)
                    val member = Member(1, "echo", unit)
                    member.updateName("echo2")
                    member.name shouldBe "echo2"
                }
                then("멤버의 이름은 30자를 넘기면 예외를 발생한다.") {
                    val unit = Unit("테스트", 1)
                    val member = Member(1, "echo", unit)
                    shouldThrow<IllegalArgumentException> {
                        member.updateName("12345678901234567890121234567890123456789012")
                    }
                }
            }
        }
    }
)