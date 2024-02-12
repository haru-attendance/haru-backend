package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class Member(
    @Column(nullable = false)
    val clubId: Long,
    name: String,
    @ManyToOne(optional = false)
    var unit: Unit
) : BaseEntity() {
    @Column(nullable = false)
    var name: String
        private set

    init {
        validateMemberName(name)
        this.name = name
    }

    private fun validateMemberName(name: String) {
        if (name.length > 30) {
            throw IllegalArgumentException("멤버의 이름은 30자를 넘기면 안된다")
        }
    }

    fun updateName(name: String) {
        validateMemberName(name)
        this.name = name
    }
}

