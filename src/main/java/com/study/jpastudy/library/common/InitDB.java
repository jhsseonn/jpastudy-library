package com.study.jpastudy.library.common;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.category.domain.Category;
import com.study.jpastudy.library.member.domain.Address;
import com.study.jpastudy.library.member.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

            Book book1 = createBook("작전명 순정", "꼬까리/들덤", Category.COMICS);
            em.persist(book1);

            Book book2 = createBook("청춘계시록", "한서", Category.COMICS);
            em.persist(book2);

            Book book3 = createBook("세레나", "정이나", Category.COMICS);
            em.persist(book3);

            Book book4 = createBook("화산귀환", "LICO/비가", Category.COMICS);
            em.persist(book4);
        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("작전명 순정", "꼬까리/들덤", Category.COMICS);
            em.persist(book1);

            Book book2 = createBook("청춘계시록", "한서", Category.COMICS);
            em.persist(book2);

            Book book3 = createBook("세레나", "정이나", Category.COMICS);
            em.persist(book3);

            Book book4 = createBook("화산귀환", "LICO/비가", Category.COMICS);
            em.persist(book4);
        }

        private Member createMember(final String name, final String city, final String street, final String zipcode) {
            return new Member(name, new Address(city, street, zipcode));
        }

        private Book createBook(final String ISBN, final String author, final Category category) {
            return new Book(ISBN, author, category);
        }
    }
}
