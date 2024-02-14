package com.haru.attendance.service

import com.haru.attendance.repository.ClubRepository
import com.haru.attendance.service.dto.ClubSaveRequest
import com.haru.attendance.service.dto.ClubSaveResponse
import org.springframework.stereotype.Service

@Service
class ClubService(val clubRepository: ClubRepository) {

    fun save(clubSaveRequest: ClubSaveRequest): ClubSaveResponse {
        val club = clubSaveRequest.toEntity()
        clubRepository.save(club)
        return ClubSaveResponse.from(club)
    }
}
