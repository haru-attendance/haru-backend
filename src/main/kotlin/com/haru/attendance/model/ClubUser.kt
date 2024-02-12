package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
class ClubUser(
    @Column(nullable = false)
    val clubId: Long,
    @Column(nullable = false)
    val userId: Long,
    @Enumerated(EnumType.STRING)
    var role: Role
) : BaseEntity()