package com.haru.attendance.controller

import com.haru.attendance.service.ClubService
import com.haru.attendance.service.dto.ClubResponse
import com.haru.attendance.service.dto.ClubResponses
import com.haru.attendance.service.dto.ClubSaveRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/clubs")
class ClubController(val clubService: ClubService) {

    @PostMapping
    fun save(@RequestBody clubSaveRequest: ClubSaveRequest): ResponseEntity<ClubResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.save(clubSaveRequest))
    }

    @GetMapping
    fun getAllClub(): ResponseEntity<ClubResponses> {
        return ResponseEntity.status(HttpStatus.OK).body(clubService.getAllClubs())
    }
}
