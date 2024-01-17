package com.study.jpastudy.library.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExceptionMessage {

    // 책
    NOT_FOUND_BOOK("입력하신 아이디에 해당하는 책 정보를 찾을 수 없습니다.");

    private final String message;
}
