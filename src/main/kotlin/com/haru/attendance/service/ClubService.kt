package com.haru.attendance.service

import com.haru.attendance.repository.ClubRepository
import com.haru.attendance.service.dto.ClubResponse
import com.haru.attendance.service.dto.ClubSaveRequest
import org.springframework.stereotype.Service

@Service
class ClubService(val clubRepository: ClubRepository) {

    fun save(clubSaveRequest: ClubSaveRequest): ClubResponse {
        val club = clubSaveRequest.toEntity()
        clubRepository.save(club)
        return ClubResponse.from(club)
    }

    fun getAllClubs(): List<ClubResponse> {
        return clubRepository.findAll().stream()
                .map { ClubResponse.from(it) }
                .toList()
    }
}
