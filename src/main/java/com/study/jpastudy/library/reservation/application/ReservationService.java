package com.study.jpastudy.library.reservation.application;

import com.study.jpastudy.library.book.application.exception.NotFoundBookException;
import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.book.infrastructure.BookRepository;
import com.study.jpastudy.library.reservation.application.dto.ReadReservationsWithBookDto;
import com.study.jpastudy.library.reservation.domain.Reservation;
import com.study.jpastudy.library.reservation.infrastructure.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    public ReadReservationsWithBookDto readAllReservationWithBook(final Long bookId,
                                                                  final int pageNo,
                                                                  final int pageSize,
                                                                  final String sortBy) {
        final Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy)
                                                                       .descending());
        final Book book = bookRepository.findById(bookId).orElseThrow(NotFoundBookException::new);
        final Page<Reservation> reservations = reservationRepository.findAllByBookId(book.getId(), pageable);

        return ReadReservationsWithBookDto.of(book, reservations.getContent());
    }
}
