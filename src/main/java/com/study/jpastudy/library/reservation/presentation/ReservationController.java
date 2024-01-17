package com.study.jpastudy.library.reservation.presentation;

import com.study.jpastudy.library.reservation.application.ReservationService;
import com.study.jpastudy.library.reservation.application.dto.ReadReservationsWithBookDto;
import com.study.jpastudy.library.reservation.presentation.dto.ReadReservationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<ReadReservationsResponse> readAllReservationsWithBook(
            @PathVariable("bookId") final Long bookId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        final ReadReservationsWithBookDto readReservationsWithBookDto = reservationService.readAllReservationWithBook(bookId, pageNo, pageSize, sortBy);
        final ReadReservationsResponse response = ReadReservationsResponse.from(readReservationsWithBookDto);

        return ResponseEntity.ok(response);
    }
}
