package com.study.jpastudy.library.common;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.category.domain.Category;
import com.study.jpastudy.library.member.domain.Address;
import com.study.jpastudy.library.member.domain.Member;
import com.study.jpastudy.library.reservation.domain.Reservation;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Member member2 = createMember("userC", "부산", "3", "3333");
            em.persist(member2);

            Member member3 = createMember("userD", "인천", "4", "4444");
            em.persist(member3);

            Member member4 = createMember("userE", "광주", "5", "5555");
            em.persist(member4);

            Book book1 = createBook("9788966263158", "가상 면접 사례로 배우는 대규모 시스템 설계 기초", "알렉스 쉬/이병준", Category.COMPUTER_MOBILE);
            em.persist(book1);

            Book book2 = createBook("9788998139766", "객체지향의 사실과 오해", "조영호", Category.COMPUTER_MOBILE);
            em.persist(book2);

            Book book3 = createBook("9791165219529", "면접을 위한 CS 전공지식 노트", "주홍철", Category.COMPUTER_MOBILE);
            em.persist(book3);

            Book book4 = createBook("9788966260959", "클린코드 Clean Code", "로버트 C. 마틴/이해영/박재호", Category.COMPUTER_MOBILE);
            em.persist(book4);

            Book book5 = createBook("9788966261024", "테스트 주도 개발", "켄트 백/김창준/강규영", Category.COMPUTER_MOBILE);
            em.persist(book5);

            Reservation reservation1 = createReservation(book1, member);
            em.persist(reservation1);

            Reservation reservation2 = createReservation(book1, member2);
            reservation2.updateReserveDate(LocalDateTime.now().minusHours(10));
            em.persist(reservation2);

            Reservation reservation3 = createReservation(book1, member3);
            reservation3.updateReserveDate(LocalDateTime.now().minusDays(3));
            em.persist(reservation3);

            Reservation reservation4 = createReservation(book1, member4);
            reservation4.updateReserveDate(LocalDateTime.now().plusDays(3));
            em.persist(reservation4);
        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Member member2 = createMember("userF", "대구", "6", "6666");
            em.persist(member2);

            Book book1 = createBook("9791130646800", "가비지타임 6~10 세트 - 전5권 ", "2사장", Category.COMICS);
            em.persist(book1);

            Book book2 = createBook("9788925563329", "대학일기 1", "자까", Category.COMICS);
            em.persist(book2);

            Book book3 = createBook("9791191841572", "대학원 탈출일지 3 ", "요다", Category.COMICS);
            em.persist(book3);

            Reservation reservation = createReservation(book1, member);
            em.persist(reservation);

            Reservation reservation2 = createReservation(book1, member2);
            em.persist(reservation2);
        }

        private Member createMember(final String name,
                                    final String city,
                                    final String street,
                                    final String zipcode) {
            return new Member(name, new Address(city, street, zipcode));
        }

        private Book createBook(final String ISBN,
                                final String title,
                                final String author,
                                final Category category) {
            return new Book(ISBN, title, author, category);
        }

        private Reservation createReservation(final Book book,
                                              final Member member) {
            return new Reservation(book, member);
        }
    }
}
