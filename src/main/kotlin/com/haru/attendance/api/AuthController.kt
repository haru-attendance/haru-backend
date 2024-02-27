package com.haru.attendance.api

import com.haru.attendance.api.dto.LoginRequest
import com.haru.attendance.api.dto.LoginResponse
import com.haru.attendance.api.dto.RegisterRequest
import com.haru.attendance.api.dto.RegisterResponse
import com.haru.attendance.service.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/auth")
class AuthController(private val authenticationService: AuthenticationService) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<RegisterResponse> {
        val response = authenticationService.register(request)
        return ResponseEntity.created(URI.create("/users/${response.id}")).body(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(authenticationService.login(request))
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}