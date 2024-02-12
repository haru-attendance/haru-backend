package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Member(
    @Column(nullable = false)
    val clubId: Long,
    @Column(nullable = false)
    var name: String,
    @ManyToOne(optional = false)
    var unit: Unit
) : BaseEntity()
