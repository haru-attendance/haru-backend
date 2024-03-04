package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "users")
class User(
    @Column(nullable = false, length = 30)
    var name: String = "",
    @Column(nullable = false, unique = true, length = 30)
    private var username: String,
    @Column(nullable = false)
    var hashedPassword: String = ""
) : BaseEntity(), UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun isEnabled() = true
    override fun isCredentialsNonExpired() = true
    override fun getPassword() = hashedPassword
    override fun getUsername() = username
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}
