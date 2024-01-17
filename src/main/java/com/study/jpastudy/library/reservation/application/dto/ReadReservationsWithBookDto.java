package com.study.jpastudy.library.reservation.application.dto;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.category.domain.Category;
import com.study.jpastudy.library.member.domain.Address;
import com.study.jpastudy.library.member.domain.Member;
import com.study.jpastudy.library.reservation.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ReadReservationsWithBookDto(BookDto bookDto, List<ReservationDto> reservationDtoList) {

    public static ReadReservationsWithBookDto of(Book book, final List<Reservation> reservations) {
        final BookDto bookDto = BookDto.from(book);
        final List<ReservationDto> reservationDtos = reservations.stream()
                                                                 .map(ReservationDto::from)
                                                                 .toList();
        return new ReadReservationsWithBookDto(bookDto, reservationDtos);
    }

    public record BookDto(
            Long id,
            String ISBN,
            String title,
            String author
    ) {

        public static BookDto from(final Book book) {
            return new BookDto(book.getId(), book.getISBN(), book.getTitle(), book.getAuthor());
        }
    }

    public record ReservationDto(
            Long id,
            Member member,
            LocalDateTime reserveDate
    ) {

        public static ReservationDto from(final Reservation reservation) {
            return new ReservationDto(reservation.getId(), reservation.getMember(), reservation.getReserveDate());
        }
    }
}
