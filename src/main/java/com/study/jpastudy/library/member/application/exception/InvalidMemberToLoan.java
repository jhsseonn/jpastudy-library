package com.study.jpastudy.library.member.application.exception;

import com.study.jpastudy.library.exception.ExceptionMessage;
import com.study.jpastudy.library.exception.JpaStudyLibraryException;

public class InvalidMemberToLoan extends JpaStudyLibraryException {

    public InvalidMemberToLoan(final ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }

    public static class InvalidInvalidMemberToLoan extends InvalidMemberToLoan {

        public InvalidInvalidMemberToLoan() {
            super(ExceptionMessage.INVALID_MEMBER_OVER_LOANED);
        }
    }

    public static class InvalidInvalidMemberNotReserved extends InvalidMemberToLoan {

        public InvalidInvalidMemberNotReserved() {
            super(ExceptionMessage.INVALID_MEMBER_NOT_RESERVED);
        }
    }
}
