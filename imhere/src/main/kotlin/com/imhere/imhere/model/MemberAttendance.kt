package com.imhere.imhere.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class MemberAttendance(
    @Column(nullable = false)
    val memberId: Long,
    @Column(nullable = false)
    val attendanceId: Long,
    @Column(nullable = false)
    val isAttended: Boolean,
    id: Long = 0L
) : BaseEntity(id)