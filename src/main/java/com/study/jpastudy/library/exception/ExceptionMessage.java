package com.study.jpastudy.library.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExceptionMessage {

    // 책
    NOT_FOUND_BOOK("입력하신 아이디에 해당하는 책 정보를 찾을 수 없습니다."),
    INVALID_BOOK_TO_LOAN("해당 도서는 이미 대출 중입니다."),

    // 회원
    NOT_FOUND_MEMBER("입력하신 아이디에 해당하는 회원 정보를 찾을 수 없습니다."),
    INVALID_MEMBER_OVER_LOANED("가능한 대출 횟수를 초과하였습니다."),
    INVALID_MEMBER_NOT_RESERVED("대출 가능한 예약자가 아닙니다.");

    private final String message;
}
