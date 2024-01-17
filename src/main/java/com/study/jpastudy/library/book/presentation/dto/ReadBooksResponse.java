package com.study.jpastudy.library.book.presentation.dto;

import com.study.jpastudy.library.book.application.dto.ReadBooksDto;

import java.util.List;

public record ReadBooksResponse(List<BookResponse> books) {

    public static ReadBooksResponse from(final ReadBooksDto readBooksDto) {
        final List<BookResponse> bookResponses = readBooksDto.books()
                                                             .stream()
                                                             .map(BookResponse::from)
                                                             .toList();
        return new ReadBooksResponse(bookResponses);
    }

    public record BookResponse(Long id,
                               String ISBN,
                               String title,
                               String author,
                               String category) {

        public static BookResponse from(final ReadBooksDto.BookDto bookDto) {
            return new BookResponse(
                    bookDto.id(),
                    bookDto.ISBN(),
                    bookDto.title(),
                    bookDto.author(),
                    bookDto.category()
            );
        }
    }
}
