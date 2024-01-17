package com.study.jpastudy.library.category.domain;

import lombok.Getter;

@Getter
public enum Category {

    NOVEL("소설"),
    EDUCATION("교육"),
    ESSAY("에세이"),
    SELF_DEVELOPMENT("자기계발서"),
    COMPUTER_MOBILE("컴퓨터/모바일"),
    COMICS("만화");

    private final String name;

    Category(final String name) {
        this.name = name;
    }

    public static Category from(final String name) {
        return Category.valueOf(name);
    }
}
