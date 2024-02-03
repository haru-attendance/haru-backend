package com.imhere.imhere.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun 기본_생성자로_User를_생성할_수_있다() {
        val user = User()

        assertAll(
                { Assertions.assertThat(user.id).isEqualTo(0L) },
                { Assertions.assertThat(user.name).isEqualTo("") },
                { Assertions.assertThat(user.username).isEqualTo("") }
        )
    }

    @Test
    fun 인자를_전달받는_생성자로_User를_생성할_수_있다() {
        val user = User("hanbin", "konghana", "123")

        assertAll(
                { Assertions.assertThat(user.id).isEqualTo(0L) },
                { Assertions.assertThat(user.name).isEqualTo("hanbin") },
                { Assertions.assertThat(user.username).isEqualTo("konghana") }
        )
    }
}


