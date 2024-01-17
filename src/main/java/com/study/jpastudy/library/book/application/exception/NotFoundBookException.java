package com.study.jpastudy.library.book.application.exception;

import com.study.jpastudy.library.exception.ExceptionMessage;
import com.study.jpastudy.library.exception.JpaStudyLibraryException;

public class NotFoundBookException extends JpaStudyLibraryException {

    public NotFoundBookException() {
        super(ExceptionMessage.NOT_FOUND_BOOK);
    }
}
