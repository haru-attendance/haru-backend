package com.imhere.imhere.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "groups")
class Group(
    @Column(nullable = false, length = 30)
    val name: String,
    id: Long = 0L
) : BaseEntity(id)