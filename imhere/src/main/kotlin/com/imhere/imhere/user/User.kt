package com.imhere.imhere.user

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,
    private val name: String,
    private val userId: String,
    private val password: String
) {

    constructor() : this(
        null,
        "",
        "",
        ""
    )
}
