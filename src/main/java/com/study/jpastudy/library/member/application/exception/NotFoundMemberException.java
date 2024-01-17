package com.study.jpastudy.library.member.application.exception;

import com.study.jpastudy.library.exception.ExceptionMessage;
import com.study.jpastudy.library.exception.JpaStudyLibraryException;

public class NotFoundMemberException extends JpaStudyLibraryException {

    public NotFoundMemberException() {
        super(ExceptionMessage.NOT_FOUND_MEMBER);
    }
}
