package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "users")
class User(
    @Column(nullable = false, length = 30)
    val name: String = "",
    @Column(nullable = false)
    val username: String = "",
    @Column(nullable = false)
    val password: String = ""
) : BaseEntity()
