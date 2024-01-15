package com.study.jpastudy.library.book.infrastructure;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.category.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Long, Book> {

    @Query(""" 
            SELECT b
            FROM Book b
            WHERE b.category = :category
            """)
    Page<Book> findAllByCategoryId(final Category category,
                                   final Pageable pageable);
}
