package com.haru.attendance.api.dto

data class RegisterResponse(
    val token: String,
    val id: Long,
    val username: String,
    val name: String
)
