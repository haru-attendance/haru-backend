package com.haru.attendance.model

import com.haru.attendance.exception.UnitServiceException
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Unit(
    name: String,
    @Column(nullable = false)
    val clubId: Long
) : BaseEntity() {
    @Column(nullable = false)
    var name: String
        private set

    init {
        validateUnitName(name)
        this.name = name
    }

    private fun validateUnitName(name: String) {
        if (name.length > 10) {
            throw UnitServiceException.UnitNameException(name)
        }
    }
}
