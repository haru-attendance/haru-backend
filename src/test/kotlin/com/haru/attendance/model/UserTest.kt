package com.haru.attendance.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UserTest : BehaviorSpec(
    {
        given("User를 생성할 때") {
            `when`("기본 생성자로 생성할 경우") {
                then("id는 0이고, name과 username은 빈 문자열이다.") {
                    val user = User()
                    user.id shouldBe 0L
                    user.name shouldBe ""
                    user.username shouldBe ""
                }
            }
            `when`("인자를 전달받는 생성자로 생성할 경우") {
                then("id는 0이고, name과 username은 전달받은 값이다.") {
                    val user = User("hanbin", "konghana", "123")
                    user.id shouldBe 0L
                    user.name shouldBe "hanbin"
                    user.username shouldBe "konghana"
                }
            }
        }
    }
)


