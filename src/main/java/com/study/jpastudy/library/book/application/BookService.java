package com.study.jpastudy.library.book.application;

import com.study.jpastudy.library.book.domain.Book;
import com.study.jpastudy.library.book.infrastructure.BookRepository;
import com.study.jpastudy.library.book.application.dto.ReadBooksDto;
import com.study.jpastudy.library.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public ReadBooksDto readAllBooksWithCategory(final Long categoryId, final int pageNo, final int pageSize, final String sortBy) {
        final Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        final Page<Book> readAllBooks = bookRepository.findAllByCategoryId(Category.from(categoryId), pageable);

        return ReadBooksDto.from(readAllBooks.getContent());
    }
}
