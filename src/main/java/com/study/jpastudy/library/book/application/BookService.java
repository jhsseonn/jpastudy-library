package com.study.jpastudy.library.book.application;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.book.infrastructure.BookRepository;
import com.study.jpastudy.library.book.application.dto.ReadBooksDto;
import com.study.jpastudy.library.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ReadBooksDto readAllBooksWithCategory(final Long categoryId) {
        final List<Book> readAllBooks = bookRepository.findAllByCategoryId(Category.from(categoryId));

        return ReadBooksDto.from(readAllBooks);
    }
}
