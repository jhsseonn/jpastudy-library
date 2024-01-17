package com.study.jpastudy.library.reservation.infrastructure;

import com.study.jpastudy.library.reservation.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            SELECT r
            FROM Reservation r
            JOIN FETCH r.book rb
            JOIN FETCH r.member
            WHERE rb.id = :bookId
            ORDER BY r.reserveDate
            """)
    Page<Reservation> findAllByBookId(final Long bookId,
                                      final Pageable pageable);
}
