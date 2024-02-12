package com.haru.attendance.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class AttendanceMember(
    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    val member: Member,
    @ManyToOne(cascade = [CascadeType.ALL], optional = false)
    val attendance: Attendance,
    @Column(nullable = false)
    val isAttended: Boolean,
    @Column(nullable = false)
    val attendedAt: LocalDateTime
) : BaseEntity()