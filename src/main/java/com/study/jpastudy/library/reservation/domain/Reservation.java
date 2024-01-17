package com.study.jpastudy.library.reservation.domain;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString(exclude = {"book", "member"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "reservation_book"))
    private Book book ;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "reservation_member"))
    private Member member;

    @Column(nullable = false)
    private LocalDateTime reserveDate = LocalDateTime.now();

    public Reservation(final Book book, final Member member){
        updateReservationInfo(book, member);
    }

    private void updateReservationInfo(final Book book, final Member member){
        this.book = book;
        this.member = member;
        book.getReservedMembers().add(this);
        member.getReservedBooks().add(this);
    }

    public void updateReserveDate(final LocalDateTime localDateTime){
        this.reserveDate = localDateTime;
    }
}
