package com.haru.attendance.integration

import com.haru.attendance.api.dto.LoginRequest
import com.haru.attendance.api.dto.RegisterRequest
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.spring.SpringExtension
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthenticationTest : AnnotationSpec() {

    @LocalServerPort
    private var port = 0
    override fun extensions(): List<Extension> {
        return listOf(SpringExtension)
    }

    @Test
    fun `유저를 등록할 수 있다`() {
        val request = RegisterRequest("하루", "haru", "password")
        val validatableResponse = registerUser(request)

        validatableResponse.statusCode(201)
        validatableResponse.body("username", equalTo("haru"))
        validatableResponse.body("name", equalTo("하루"))
        validatableResponse.body("token", notNullValue())
    }

    @Test
    fun `로그인 할 수 있다`() {
        val request = RegisterRequest("하루", "haru", "password")
        registerUser(request)


        val loginRequest = LoginRequest("haru", "password")
        val loginResponse = Given {
            port(port)
            body(loginRequest)
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
        } When {
            post("/auth/login")
        } Then {
            statusCode(200)
        }

        loginResponse.body("token", notNullValue())
    }

    @Test
    fun `이미 있는 계정의 username과 중복된 username으로 회원가입을 시도하면 예외를 발생한다`() {
        val request = RegisterRequest("하루", "haru", "password")
        registerUser(request)

        val validatableResponse = registerUser(request)

        validatableResponse.statusCode(400)
    }

    private fun registerUser(request: RegisterRequest): ValidatableResponse {
        return Given {
            port(port)
            body(request)
            contentType(ContentType.JSON)
            accept(ContentType.JSON)
        } When {
            post("/auth/register")
        } Then {
        }
    }
}