package com.haru.attendance.service

import com.haru.attendance.model.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JwtTokenServiceTest : BehaviorSpec() {

    @Autowired
    private lateinit var jwtTokenService: JwtTokenService

    override fun extensions() = listOf(SpringExtension)


    init {
        Given("유저 아이디가 주어진 경우") {
            val user = User("하루루", "haru", "password")
            val token = jwtTokenService.generateToken(user)
            When("토큰을 생성하면") {
                Then("유효한 토큰이 생성된다") {
                    token shouldNotBe null
                    jwtTokenService.isTokenValid(token, user) shouldBe true
                }
                Then("유저네임이 추출된다") {
                    val username = jwtTokenService.extractUsername(token)
                    username shouldBe "haru"
                }
            }
        }
    }
}