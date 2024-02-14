package com.haru.attendance.model

import com.haru.attendance.exception.ClubServiceException
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
            throw ClubServiceException.ClubNameException(name)
        }
    }

    fun updateName(name: String) {
        validateName(name)
        this.name = name
    }
}
