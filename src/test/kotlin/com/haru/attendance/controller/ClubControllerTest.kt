package com.haru.attendance.controller

import com.haru.attendance.service.dto.ClubChangeRequest
import com.haru.attendance.service.dto.ClubResponse
import com.haru.attendance.service.dto.ClubSaveRequest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClubControllerTest {

    @LocalServerPort
    var port = 0

    @Test
    fun `사용자가 club 이름을 전달하고 post 요청을 보내면 club이 생성된다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        create_club(path, clubName)
    }

    @Test
    fun `사용자가 30자가 넘는 club 이름을 전달하고 post요청을 보내면 예외가 발생한다`() {
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

    @Test
    fun `사용자가 속해있는 club들의 이름을 조회할 수 있다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        create_club(path, clubName)

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            get(path)
        } Then {
            body(
                    "clubs", Matchers.hasSize<Any>(1),
                    "clubs[0].id", Matchers.greaterThan(0),
                    "clubs[0].name", Matchers.equalTo(clubName)
            )
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `사용자가 속해있는 클럽의 정보를 변경할 수 있다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"
        val changedClubName = "굿모닝 클럽"

        val clubResponse = create_club(path, clubName)

        Given {
            port(port)
            contentType(ContentType.JSON)
            body(ClubChangeRequest(changedClubName))
        } When {
            put(path + "/" + clubResponse.id)
        } Then {
            body(
                    "id", Matchers.greaterThan(0),
                    "name", Matchers.equalTo(changedClubName)
            )
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `사용자가 속해있는 클럽을 하나 조회할 수 있다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        val clubResponse = create_club(path, clubName)

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            get(path + "/" + clubResponse.id)
        } Then {
            body(
                    "id", Matchers.equalTo(clubResponse.id.toInt()),
                    "name", Matchers.equalTo(clubResponse.name)
            )
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `사용자가 존재하지_않는 club id를 조회하면 예외가 발생한다`() {
        val path = "/clubs/1"

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            get(path)
        } Then {
            // TODO: 커스텀 예외 처리 구현
            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    @Test
    fun `관리자는 클럽을 삭제할 수 있다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        val clubResponse = create_club(path, clubName)

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            delete(path + "/" + clubResponse.id)
        } Then {
            statusCode(HttpStatus.NO_CONTENT.value())
        }
    }

    @Test
    fun `삭제한 클럽은 조회를 할 수 없다`() {
        val path = "/clubs"
        val clubName = "나이트 클럽"

        val clubResponse = create_club(path, clubName)

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            delete(path + "/" + clubResponse.id)
        } Then {
            statusCode(HttpStatus.NO_CONTENT.value())
        }

        Given {
            port(port)
            contentType(ContentType.JSON)
        } When {
            get(path + "/" + clubResponse.id)
        } Then {
            // TODO: 커스텀 예외 처리 구현
            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    private fun create_club(path: String, clubName: String): ClubResponse {
        return Given {
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
        } Extract {
            response().body.`as`(ClubResponse::class.java)
        }
    }
}
