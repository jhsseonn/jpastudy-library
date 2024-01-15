package com.study.jpastudy.library.category.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Category {

    NOVEL("1", "소설"),
    EDUCATION("2", "교육"),
    ESSAY("3", "에세이"),
    SELF_DEVELOPMENT("4", "자기계발서"),
    COMICS("5", "만화");

    private final String id;
    private final String name;

    Category(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Category from(final Long categoryId){
        return Category.valueOf(categoryId.toString());
    }
}
