package com.haru.attendance.service

import com.haru.attendance.api.dto.LoginRequest
import com.haru.attendance.api.dto.RegisterRequest
import com.haru.attendance.exception.UserServiceException
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthenticationServiceTest : BehaviorSpec() {
    @Autowired
    private lateinit var authenticationService: AuthenticationService

    override fun extensions() = listOf(SpringExtension)

    init {
        Given("같은 이름의 유저가 없는 경우") {
            When("이름과 유저네임과 패스워드를 입력하면") {
                val request = RegisterRequest("하루", "haru", "password")
                Then("유저를 등록할 수 있다") {
                    val response = authenticationService.register(request)
                    response.id shouldBe 1
                    response.name shouldBe "하루"
                    response.username shouldBe "haru"
                    response.token shouldNotBe null
                }
            }
        }
        Given("같은 이름의 유저가 있는 경우") {
            When("같은 이름으로 유저를 등록하려는 경우") {
                val request = RegisterRequest("하루", "haru", "password")
                authenticationService.register(request)
                Then("유저를 등록할 수 없다") {
                    shouldThrowExactly<UserServiceException.UsernameAlreadyExistsException> {
                        authenticationService.register(request)
                    }
                }
            }
        }

        Given("유저가 생성된 경우") {
            val request = RegisterRequest("하루", "haru", "password")
            authenticationService.register(request)
            When("유저 정보로 로그인을 시도하면") {
                val loginRequest = LoginRequest("haru", "password")
                Then("로그인이 가능하다") {
                    val response = authenticationService.login(loginRequest)
                    response.token shouldNotBe null
                }
            }
        }
    }
}