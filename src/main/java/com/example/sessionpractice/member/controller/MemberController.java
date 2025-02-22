package com.example.sessionpractice.member.controller;

import com.example.sessionpractice.common.consts.Const;
import com.example.sessionpractice.member.dto.MemberResponseDto;
import com.example.sessionpractice.member.dto.MemberSaveRequestDto;
import com.example.sessionpractice.member.dto.MemberSaveResponseDto;
import com.example.sessionpractice.member.dto.MemberUpdateRequestDto;
import com.example.sessionpractice.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/member")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto requestDto
    ){
        memberService.update(memberId, requestDto);
    }

    @DeleteMapping("/member")
    public void deleteById(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId){
        memberService.deleteById(memberId);
    }
}
