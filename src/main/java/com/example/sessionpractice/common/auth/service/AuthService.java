package com.example.sessionpractice.common.auth.service;

import com.example.sessionpractice.common.auth.dto.AuthLoginRequestDto;
import com.example.sessionpractice.common.auth.dto.AuthLoginResponseDto;
import com.example.sessionpractice.common.auth.dto.AuthSignupRequestDto;
import com.example.sessionpractice.member.dto.MemberSaveResponseDto;
import com.example.sessionpractice.member.entity.Member;
import com.example.sessionpractice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto requestDto) {
        Member member = new Member(requestDto.getEmail());
        memberRepository.save(member);
    }

    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("그 이메일을 가진 멤버가 없어")
        );
        return new AuthLoginResponseDto(member.getId(), member.getEmail());
    }
}
