package com.teamteam.backend.web.reservation

import com.teamteam.backend.domain.reservation.dto.ReservationReadDTO
import com.teamteam.backend.domain.reservation.dto.ReservationSummaryAdminCreateDTO
import com.teamteam.backend.domain.reservation.dto.ReservationSummaryReadDTO
import com.teamteam.backend.domain.reservation.service.ReservationService
import com.teamteam.backend.shared.dto.CommonResponse
import com.teamteam.backend.shared.security.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservation")
class ReservationController(
    private val reservationService: ReservationService
) {
    //*** read only logic ***//
    @GetMapping("/summary")
    fun findAll(): ResponseEntity<List<ReservationSummaryReadDTO>> = ResponseEntity.ok(reservationService.findAll())

    @GetMapping("/admin/summary/permit")
    fun findAllNeedPermit(): ResponseEntity<List<ReservationSummaryReadDTO>> =
        ResponseEntity.ok(reservationService.findAllNeedPermit())

    @GetMapping("/summary/me")
    fun findMyReservationSummarys(
        authentication: Authentication
    ): ResponseEntity<List<ReservationSummaryReadDTO>> =
        ResponseEntity.ok(reservationService.findMyReservationSummarys(authentication.principal as User))

    @GetMapping("/me")
    fun findMyReservation(
        authentication: Authentication
    ): ResponseEntity<List<ReservationReadDTO>> =
        ResponseEntity.ok(reservationService.findMyReservations(authentication.principal as User))

    //*** command logic ***//
    @PostMapping("/admin/summary/{roomId}")
    fun createReservationByAdmin(
        authentication: Authentication,
        @PathVariable roomId: String,
        @RequestBody dto: ReservationSummaryAdminCreateDTO
    ): ResponseEntity<ReservationSummaryReadDTO> = ResponseEntity.status(HttpStatus.CREATED)
        .body(reservationService.createReservationSummaryByAdmin(authentication.principal as User, dto, roomId))

    @DeleteMapping("/admin/summary/{summaryId}")
    fun deleteReservationSummaryByAdmin(@PathVariable summaryId: String): ResponseEntity<CommonResponse> {
        reservationService.deleteReservationSummaryByAdmin(summaryId = summaryId)
        return ResponseEntity.ok(
            CommonResponse(
                message = "delete reservation summary success",
                code = "delete_reservation_summary_success",
                status = 200
            )
        )
    }
}