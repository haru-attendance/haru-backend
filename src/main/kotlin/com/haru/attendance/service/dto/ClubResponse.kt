package com.haru.attendance.service.dto

import com.haru.attendance.model.Club

data class ClubResponse private constructor(val id: Long, val name: String) {

    companion object {
        fun from(club: Club): ClubResponse {
            return ClubResponse(club.id, club.name)
        }
    }
}
