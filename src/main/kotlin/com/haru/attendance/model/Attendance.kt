package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDate

@Entity
class Attendance(
    @Column(nullable = false)
    var date: LocalDate = LocalDate.now(),
    @Column(nullable = false)
    val clubId: Long,
) : BaseEntity()
