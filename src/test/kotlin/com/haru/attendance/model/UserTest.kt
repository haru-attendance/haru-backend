package com.haru.attendance.model

import io.kotest.core.spec.style.BehaviorSpec
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll

class UserTest : BehaviorSpec(
    {
        given("User를 생성할 때") {
            `when`("기본 생성자로 생성할 경우") {
                then("id는 0이고, name과 username은 빈 문자열이다.") {
                    val user = User()

                    assertAll(
                        { Assertions.assertThat(user.id).isEqualTo(0L) },
                        { Assertions.assertThat(user.name).isEqualTo("") },
                        { Assertions.assertThat(user.username).isEqualTo("") }
                    )
                }
            }
            `when`("인자를 전달받는 생성자로 생성할 경우") {
                then("id는 0이고, name과 username은 전달받은 값이다.") {
                    val user = User("hanbin", "konghana", "123")

                    assertAll(
                        { Assertions.assertThat(user.id).isEqualTo(0L) },
                        { Assertions.assertThat(user.name).isEqualTo("hanbin") },
                        { Assertions.assertThat(user.username).isEqualTo("konghana") }
                    )
                }
            }
        }
    }
)


