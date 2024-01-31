package com.imhere.imhere.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class AttendanceMember(
    @Column(nullable = false)
    val memberId: Long,
    @Column(nullable = false)
    val attendanceId: Long,
    @Column(nullable = false)
    val isAttended: Boolean,
) : BaseEntity()