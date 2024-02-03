package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "groups")
class Group(
    @Column(nullable = false, length = 30)
    var name: String,
) : BaseEntity() {
    init {
        if (name.length > 30) {
            throw IllegalArgumentException("그룹의 이름은 30자를 넘기면 안된다")
        }
    }
}