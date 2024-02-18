package com.haru.attendance.controller

import com.haru.attendance.service.dto.ClubSaveRequest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClubControllerTest {

    @LocalServerPort
    var port = 0

    @Test
    fun 사용자가_club_이름을_전달하고_post_요청을_보내면_club이_생성된다() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        Given {
            port(port)
            contentType(ContentType.JSON)
            body(ClubSaveRequest(clubName))
        } When {
            post(path)
        } Then {
            body(
                    "id", Matchers.greaterThan(0),
                    "name", Matchers.equalTo(clubName)
            )
            statusCode(HttpStatus.CREATED.value())
        }
    }

    @Test
    fun 사용자가_30자가_넘는_club_이름을_전달하고_post_요청을_보내면_club이_생성된다() {
        val path = "/clubs"
        val clubName = "1234567890123456789012345678901234567890"

        Given {
            port(port)
            contentType(ContentType.JSON)
            body(ClubSaveRequest(clubName))
        } When {
            post(path)
        } Then {
            // TODO: 커스텀 예외 구현
            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }
}
