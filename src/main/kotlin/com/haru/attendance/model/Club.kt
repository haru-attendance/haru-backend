package com.haru.attendance.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Club(
    name: String
) : BaseEntity() {
    @Column(nullable = false)
    var name: String
        private set

    init {
        validateName(name)
        this.name = name
    }

    private fun validateName(name: String) {
        if (name.length > 30) {
            throw IllegalArgumentException("그룹의 이름은 30자를 넘기면 안된다")
        }
    }

    fun updateName(name: String) {
        validateName(name)
        this.name = name
    }
}