package com.haru.attendance.service

import com.haru.attendance.api.dto.LoginRequest
import com.haru.attendance.api.dto.LoginResponse
import com.haru.attendance.api.dto.RegisterRequest
import com.haru.attendance.api.dto.RegisterResponse
import com.haru.attendance.exception.UserServiceException
import com.haru.attendance.model.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtTokenService: JwtTokenService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): RegisterResponse {
        val user = request.toEntity(passwordEncoder)
        userRepository.findByUsername(user.username)?.let {
            throw UserServiceException.UsernameAlreadyExistsException(user.username)
        }
        userRepository.save(user)
        val token = jwtTokenService.generateToken(user)
        return RegisterResponse(token, user.id, user.username, user.name)
    }

    fun login(request: LoginRequest): LoginResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.username, request.password))
        val user = userRepository.findByUsername(request.username)
            ?: throw UserServiceException.UsernameNotFoundException(request.username)
        return LoginResponse(jwtTokenService.generateToken(user))
    }

}