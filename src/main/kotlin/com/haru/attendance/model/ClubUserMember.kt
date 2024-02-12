package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ClubUserMember(
    @Column(nullable = false)
    val clubUserId: Long,
    @Column(nullable = false)
    val memberId: Long
) : BaseEntity()