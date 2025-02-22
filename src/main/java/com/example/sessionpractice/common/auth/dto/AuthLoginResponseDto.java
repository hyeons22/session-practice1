package com.example.sessionpractice.common.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginResponseDto {

    private final Long memberId;
    private final String email;

    public AuthLoginResponseDto(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}
