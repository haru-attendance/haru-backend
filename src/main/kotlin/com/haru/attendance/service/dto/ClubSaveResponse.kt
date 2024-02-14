package com.haru.attendance.service.dto

import com.haru.attendance.model.Club

data class ClubSaveResponse private constructor(val id: Long, val name: String) {

    companion object {
        fun from(club: Club): ClubSaveResponse {
            return ClubSaveResponse(club.id, club.name)
        }
    }
}
