package com.study.jpastudy.library.reservation.presentation.dto;

import com.study.jpastudy.library.member.domain.Address;
import com.study.jpastudy.library.reservation.application.dto.ReadReservationsWithBookDto;

import java.time.LocalDateTime;
import java.util.List;

public record ReadReservationsResponse(BookInfoDto bookInfo, List<ReservationResponse> reservations) {

    public static ReadReservationsResponse from(final ReadReservationsWithBookDto readReservationsWithBookDto) {
        final BookInfoDto bookInfoDto = BookInfoDto.from(readReservationsWithBookDto.bookDto());
        final List<ReservationResponse> reservationResponses = readReservationsWithBookDto.reservationDtoList()
                                                                                          .stream()
                                                                                          .map(ReservationResponse::from)
                                                                                          .toList();

        return new ReadReservationsResponse(bookInfoDto, reservationResponses);
    }

    public record BookInfoDto(Long id, String ISBN, String title, String author) {

        public static BookInfoDto from(final ReadReservationsWithBookDto.BookDto bookDto) {
            return new BookInfoDto(bookDto.id(), bookDto.ISBN(), bookDto.title(), bookDto.author());
        }
    }

    public record ReservationResponse(Long id,
                                      Long memberId,
                                      String memberName,
                                      Address address,
                                      LocalDateTime reserveDate) {

        public static ReservationResponse from(final ReadReservationsWithBookDto.ReservationDto reservationDto) {
            return new ReservationResponse(
                    reservationDto.id(),
                    reservationDto.member().getId(),
                    reservationDto.member().getName(),
                    reservationDto.member().getAddress(),
                    reservationDto.reserveDate()
            );
        }
    }
}
