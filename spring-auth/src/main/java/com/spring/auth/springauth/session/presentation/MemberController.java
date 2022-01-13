package com.spring.auth.springauth.session.presentation;

import com.spring.auth.springauth.session.application.MemberService;
import com.spring.auth.springauth.session.domain.LoginFailedException;
import com.spring.auth.springauth.session.dto.*;
import com.spring.auth.springauth.session.infrastructure.config.CurrentLoginMemberArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        Long memberId = memberService.create(memberRequestDto);
        return ResponseEntity.ok(new MemberResponseDto(memberId));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request, HttpSession session) {
        try {
            LoginMember loginMember = memberService.login(request.getEmail(), request.getPassword());
            session.setAttribute(CurrentLoginMemberArgumentResolver.LOGIN_ATTRIBUTE_NAME, loginMember);
            return ResponseEntity.ok(AuthResponseDto.success(loginMember));
        } catch (LoginFailedException e) {
            return ResponseEntity.ok(AuthResponseDto.fail(e.getResult()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseEntity.ok("SUCCESS");
    }
}
