package com.haru.attendance.service

import com.haru.attendance.exception.ClubServiceException
import com.haru.attendance.model.Club
import com.haru.attendance.repository.ClubRepository
import com.haru.attendance.service.dto.ClubChangeRequest
import com.haru.attendance.service.dto.ClubSaveRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk
import java.util.*

class ClubServiceTest : BehaviorSpec({
    val clubRepository = mockk<ClubRepository>()
    val clubService = ClubService(clubRepository)

    given("클럽 생성 dto가 전달되고") {
        val clubName = "나이트 클럽"
        val clubSaveRequest = ClubSaveRequest(clubName)
        val savedClub = clubSaveRequest.toEntity()
        every { clubRepository.save(any()) } returns savedClub // 이미 지정된 객체를 전달해야 올바르게 동작

        `when`("클럽을 영속화하면") {
            val clubResponse = clubService.save(clubSaveRequest)

            then("클럽이 저장된다.") {
                clubResponse.name shouldBeEqual savedClub.name
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

    given("-") {
        val clubs = listOf(Club("콩 클럽"), Club("에코 클럽"))
        every { clubRepository.findAll() } returns clubs

        `when`("-") {
            val clubs = clubService.getAllClubs()

            then("동아리 목록을 조회할 수 있다.") {
                clubs.clubs shouldHaveSize 2
            }
        }
    }

    given("동아리 번호가 주어지고") {
        val club = Club("콩 클럽")
        val clubId = 1L
        every { clubRepository.findById(clubId) } returns Optional.of(club)

        `when`("동아리 목록을 조회하면") {
            val clubResponse = clubService.getOneClub(clubId)

            then("해당 동아리 정보를 조회할 수 있다.") {
                clubResponse.name shouldBeEqual club.name
            }
        }
    }

    given("존재하지 않는 동아리 번호가 주어지고") {
        val clubId = 1L
        every { clubRepository.findById(clubId) } returns Optional.empty()

        `when`("동아리를 조회하면") {
            then("예외가 발생한다.") {
                shouldThrow<ClubServiceException.NonClubIdException> {
                    clubService.getOneClub(clubId)
                }
            }
        }
    }

    given("이름을 변경할 동아리 번호와 이름이 주어지고") {
        val clubId = 1L
        val clubChangeRequest = ClubChangeRequest("에코 클럽")
        val club = Club("콩 클럽")
        every { clubRepository.findById(clubId) } returns Optional.of(club)

        `when`("이름을 변경하면") {
            val clubResponse = clubService.changeClub(clubId, clubChangeRequest)

            then("해당 동아리 이름을 변경할 수 있다.") {
                clubResponse.name shouldBeEqual clubChangeRequest.name
            }
        }
    }

    given("존재하지 않는 동아리 번호와 이름이 주어지고") {
        val clubId = 1L
        val clubChangeRequest = ClubChangeRequest("에코 클럽")
        every { clubRepository.findById(clubId) } returns Optional.empty()

        `when`("이름을 변경하면") {

            then("예외가 발생한다.") {
                shouldThrow<ClubServiceException.NonClubIdException> {
                    clubService.changeClub(clubId, clubChangeRequest)
                }
            }
        }
    }
})
