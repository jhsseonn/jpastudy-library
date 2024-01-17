package com.study.jpastudy.library.book.application.exception;

import com.study.jpastudy.library.exception.ExceptionMessage;
import com.study.jpastudy.library.exception.JpaStudyLibraryException;

public class InvalidBookToLoan extends JpaStudyLibraryException {

    public InvalidBookToLoan(final ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }

    public static class InvalidInvalidBookToLoan extends InvalidBookToLoan{

        public InvalidInvalidBookToLoan() {
            super(ExceptionMessage.INVALID_BOOK_TO_LOAN);
        }
    }
}
