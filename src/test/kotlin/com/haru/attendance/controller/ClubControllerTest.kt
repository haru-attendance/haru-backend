package com.haru.attendance.controller

import com.haru.attendance.service.dto.ClubSaveRequest
import com.haru.attendance.service.dto.ClubResponse
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.longs.shouldBeGreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClubControllerTest : BehaviorSpec() {

    @LocalServerPort
    var port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    init {
        extension(SpringExtension)

        given("사용자가 /clubs 주소로 club 이름을 전달하고") {
            val url = "http://localhost:"
            val path = "/clubs"
            val clubName = "나이트 클럽"

            `when`("post 요청을 보내면") {
                val clubSaveRequest = ClubSaveRequest(clubName)
                val response = restTemplate.postForEntity(url + port + path, clubSaveRequest, ClubResponse::class.java)

                then("club이 생성된다.") {
                    response.body!!.name shouldBeEqual clubName
                    response.body!!.id shouldBeGreaterThan 0L
                    response.statusCode shouldBeEqual HttpStatus.CREATED
                }
            }
        }

        given("사용자가 /clubs 주소로 30자 이상의 club 이름을 전달하고") {
            val url = "http://localhost:"
            val path = "/clubs"
            val clubName = "1234567890123456789012345678901234567890"

            `when`("post 요청을 보내면") {
                val clubSaveRequest = ClubSaveRequest(clubName)
                val response = restTemplate.postForEntity(url + port + path, clubSaveRequest, ClubResponse::class.java)

                then("404 예외가 발생한다.") {
                    // TODO: 커스텀 예외 구현
                    response.statusCode shouldBeEqual HttpStatus.INTERNAL_SERVER_ERROR
                }
            }
        }
    }
}
