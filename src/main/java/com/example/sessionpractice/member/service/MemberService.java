package com.example.sessionpractice.member.service;

import com.example.sessionpractice.member.dto.MemberResponseDto;
import com.example.sessionpractice.member.dto.MemberSaveRequestDto;
import com.example.sessionpractice.member.dto.MemberSaveResponseDto;
import com.example.sessionpractice.member.dto.MemberUpdateRequestDto;
import com.example.sessionpractice.member.entity.Member;
import com.example.sessionpractice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getEmail()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버가 없어")
        );
        return new MemberResponseDto(member.getId(), member.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("그런 멤버가 없어")
        );
        member.update(requestDto.getEmail());
    }

    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
