package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Unit(
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    val clubId: Long
) : BaseEntity()