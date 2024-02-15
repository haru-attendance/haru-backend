package com.haru.attendance.model

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
            throw IllegalArgumentException("유닛의 이름은 10자를 넘기면 안된다")
        }
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//        if (!super.equals(other)) return false
//
//        other as Unit
//
//        if (name != other.name) return false
//        if (clubId != other.clubId) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = super.hashCode()
//        result = 31 * result + name.hashCode()
//        result = 31 * result + clubId.hashCode()
//        return result
//    }
}