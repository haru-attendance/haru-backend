package com.haru.attendance.api.dto

import com.haru.attendance.model.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegisterRequest(
    val name: String,
    val username: String,
    val password: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder) = User(name, username, passwordEncoder.encode(password))
}