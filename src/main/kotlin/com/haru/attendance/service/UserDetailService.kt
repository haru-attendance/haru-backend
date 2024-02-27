package com.haru.attendance.service

import com.haru.attendance.exception.UserServiceException
import com.haru.attendance.model.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw UserServiceException.UsernameNotFoundException(username)
    }
}