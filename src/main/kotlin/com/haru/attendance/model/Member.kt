package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Member(
    var userId: Long?,
    @Column(nullable = false)
    val groupId: Long,
    @Column(nullable = false)
    var subGroupId: Long,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var role: Role
) : BaseEntity()
