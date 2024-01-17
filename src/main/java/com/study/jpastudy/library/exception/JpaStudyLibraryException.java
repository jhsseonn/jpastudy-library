package com.study.jpastudy.library.exception;

import lombok.Getter;

@Getter
public class JpaStudyLibraryException extends RuntimeException{

    private final String message;

    public JpaStudyLibraryException(final ExceptionMessage exceptionMessage) {
        this.message = exceptionMessage.getMessage();
    }
}
