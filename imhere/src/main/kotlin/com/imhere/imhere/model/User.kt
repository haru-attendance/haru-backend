package com.imhere.imhere.model

import jakarta.persistence.Entity

@Entity(name = "users")
class User(
        val name: String = "",
        val username: String = "",
        val password: String = ""
) : BaseEntity() {
}
