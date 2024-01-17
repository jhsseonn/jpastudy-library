package com.study.jpastudy.library.reservation.domain;

import com.study.jpastudy.library.book.domain.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString(exclude = {"book", "reservation"})
public class ReserveBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "reserve_book_book"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false, foreignKey = @ForeignKey(name = "reserve_book_reservation"))
    private Reservation reservation;
}
