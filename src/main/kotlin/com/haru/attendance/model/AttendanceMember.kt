package com.haru.attendance.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class AttendanceMember(
    @Column(nullable = false)
    val memberId: Long,
    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    val attendance: Attendance,
    @Column(nullable = false)
    val isAttended: Boolean,
    @Column(nullable = false)
    val attendedAt: LocalDateTime
) : BaseEntity()