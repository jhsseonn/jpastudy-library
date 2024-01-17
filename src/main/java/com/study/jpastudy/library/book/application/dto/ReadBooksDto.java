package com.study.jpastudy.library.book.application.dto;

import com.study.jpastudy.library.book.domain.Book;

import java.util.List;

public record ReadBooksDto(List<BookDto> books) {

    public static ReadBooksDto from(final List<Book> books) {
        final List<BookDto> bookDtos = books.stream()
                                            .map(BookDto::from)
                                            .toList();

        return new ReadBooksDto(bookDtos);
    }

    public record BookDto(
            Long id,
            String ISBN,
            String title,
            String author,
            String category
    ) {

        public static BookDto from(final Book book) {
            return new BookDto(book.getId(), book.getISBN(), book.getTitle(), book.getAuthor(), book.getCategory()
                                                                                   .getName());
        }
    }
}
