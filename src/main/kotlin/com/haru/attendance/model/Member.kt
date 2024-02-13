package com.haru.attendance.model

import com.haru.attendance.exception.MemberServiceException
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
            throw MemberServiceException.MemberNameException(name)
        }
    }

    fun updateName(name: String) {
        validateMemberName(name)
        this.name = name
    }
}

