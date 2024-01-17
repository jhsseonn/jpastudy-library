package com.study.jpastudy.library.book.domain;

import com.study.jpastudy.library.category.domain.Category;
import com.study.jpastudy.library.reservation.domain.Reservation;
import com.study.jpastudy.library.reservation.domain.ReserveBook;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString(exclude = "reservedMembers")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String ISBN;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Reservation> reservedMembers = new ArrayList<>();

    @Column(nullable = false)
    private boolean isLoaned = false;

    @Column(nullable = false)
    private boolean isReserved = false;

    public Book(final String ISBN,
                final String title,
                final String author,
                final Category category) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
