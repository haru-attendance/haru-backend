package com.haru.attendance.service.dto

import com.haru.attendance.model.Club

data class ClubSaveRequest(val name: String) {

    fun toEntity(): Club {
        return Club(name)
    }
}
