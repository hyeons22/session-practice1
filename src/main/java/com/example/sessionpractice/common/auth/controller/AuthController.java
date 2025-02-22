package com.example.sessionpractice.common.auth.controller;

import com.example.sessionpractice.common.auth.dto.AuthLoginRequestDto;
import com.example.sessionpractice.common.auth.dto.AuthLoginResponseDto;
import com.example.sessionpractice.common.auth.dto.AuthSignupRequestDto;
import com.example.sessionpractice.common.auth.service.AuthService;
import com.example.sessionpractice.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto requestDto){
        authService.signup(requestDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequestDto requestDto, HttpServletRequest request){
        AuthLoginResponseDto result = authService.login(requestDto);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }
}
