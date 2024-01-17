package com.study.jpastudy.library.member.domain;

import com.study.jpastudy.library.reservation.domain.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@ToString(exclude = "reservedBooks")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    @Column(nullable = false, columnDefinition = "text")
    private Address address;

    @Column(nullable = false)
    private int loanCount = 0;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Reservation> reservedBooks = new ArrayList<>();

    public Member(final String name, final Address address){
        this.name = name;
        this.address = address;
    }
}
