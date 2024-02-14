package com.haru.attendance.service

import com.haru.attendance.exception.ClubServiceException
import com.haru.attendance.repository.ClubRepository
import com.haru.attendance.service.dto.ClubSaveRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk

class ClubServiceTest : BehaviorSpec({
    val clubRepository = mockk<ClubRepository>()
    val clubService = ClubService(clubRepository)

    given("클럽 생성 dto가 전달되고") {
        val clubName = "나이트 클럽"
        val clubSaveRequest = ClubSaveRequest(clubName)

        `when`("클럽을 영속화하면") {
            val savedClub = clubSaveRequest.toEntity()
            every { clubRepository.save(any()) } returns savedClub // 이미 지정된 객체를 전달해야 올바르게 동작

            then("클럽이 저장된다.") {

                val clubSaveResponse = clubService.save(clubSaveRequest)

                clubSaveResponse.name shouldBeEqual savedClub.name
            }
        }
    }

    given("이름이 30자를 초과하는 클럽 생성 dto가 전달되고") {
        val clubName = "1234567890123456789012345678901"
        val clubSaveRequest = ClubSaveRequest(clubName)

        `when`("클럽 도메인으로 변경하면") {
            then("예외가 발생한다.") {
                shouldThrow<ClubServiceException.ClubNameException> {
                    clubService.save(clubSaveRequest)
                }
            }
        }
    }
})
