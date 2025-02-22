package com.example.sessionpractice.todo.dto;

import lombok.Getter;

@Getter
public class TodoResponseDto {

    private final Long id;
    private final String content;

    public TodoResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
