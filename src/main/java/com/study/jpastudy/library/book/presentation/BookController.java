package com.study.jpastudy.library.book.presentation;

import com.study.jpastudy.library.book.application.BookService;
import com.study.jpastudy.library.book.application.dto.ReadBooksDto;
import com.study.jpastudy.library.book.presentation.dto.ReadBooksResponse;
import com.study.jpastudy.library.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/all")
    public ResponseEntity<ReadBooksResponse> readAllBooksWithCategory(@RequestParam(name = "category", required = false, defaultValue = "") final Category categoryName,
                                                                      @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                      @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                                                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        final ReadBooksDto readBooksDtos = bookService.readAllBooksWithCategory(categoryName, pageNo, pageSize, sortBy);
        final ReadBooksResponse response = ReadBooksResponse.from(readBooksDtos);

        return ResponseEntity.ok(response);
    }
}
