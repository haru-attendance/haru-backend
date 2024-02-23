package com.haru.attendance.service

import com.haru.attendance.exception.ClubServiceException
import com.haru.attendance.model.Club
import com.haru.attendance.repository.ClubRepository
import com.haru.attendance.service.dto.ClubChangeRequest
import com.haru.attendance.service.dto.ClubResponse
import com.haru.attendance.service.dto.ClubResponses
import com.haru.attendance.service.dto.ClubSaveRequest
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class ClubService(val clubRepository: ClubRepository) {

    fun save(clubSaveRequest: ClubSaveRequest): ClubResponse {
        val club = clubSaveRequest.toEntity()
        clubRepository.save(club)
        return ClubResponse.from(club)
    }

    fun getAllClubs(): ClubResponses {
        return ClubResponses(clubRepository.findAll().stream()
                .map { ClubResponse.from(it) }
                .toList())
    }

    fun getOneClub(clubId: Long): ClubResponse {
        return ClubResponse.from(getClubById(clubId))
    }

    private fun getClubById(clubId: Long): Club = clubRepository.findById(clubId)
            .getOrElse { throw ClubServiceException.NonClubIdException(clubId) }

    fun changeClub(clubId: Long, clubChangeRequest: ClubChangeRequest): ClubResponse {
        val club = getClubById(clubId)
        club.updateName(clubChangeRequest.name)
        return ClubResponse.from(club)
    }
}
