package com.imhere.imhere.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Attendance(
    @Column(nullable = false)
    val date: LocalDate = LocalDate.now(),
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "group_id", nullable = false, updatable = false,
        foreignKey = ForeignKey(name = "fk_attendance_group_id_ref_group_id")
    )
    val group: Group,
) : BaseEntity()
