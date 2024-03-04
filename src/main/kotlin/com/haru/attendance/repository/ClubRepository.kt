package com.haru.attendance.repository

import com.haru.attendance.model.Club
import org.springframework.data.jpa.repository.JpaRepository

interface ClubRepository : JpaRepository<Club, Long> {

}
