package com.haru.attendance.controller

import com.haru.attendance.service.ClubService
import com.haru.attendance.service.dto.ClubChangeRequest
import com.haru.attendance.service.dto.ClubResponse
import com.haru.attendance.service.dto.ClubResponses
import com.haru.attendance.service.dto.ClubSaveRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{clubId}")
    fun getClubById(@PathVariable clubId: Long): ResponseEntity<ClubResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(clubService.getOneClub(clubId))
    }

    @PutMapping("/{clubId}")
    fun changeClubInfo(@PathVariable clubId: Long, @RequestBody clubChangeRequest: ClubChangeRequest): ResponseEntity<ClubResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(clubService.changeClub(clubId, clubChangeRequest))
    }

    @DeleteMapping("/{clubId}")
    fun deleteClub(@PathVariable clubId: Long): ResponseEntity<Unit> {
        clubService.deleteClub(clubId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
