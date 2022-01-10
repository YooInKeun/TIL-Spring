package com.spring.auth.springauth.member.presentation;

import com.spring.auth.springauth.member.application.MemberService;
import com.spring.auth.springauth.member.dto.MemberRequestDto;
import com.spring.auth.springauth.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        Long memberId = memberService.create(memberRequestDto);
        return ResponseEntity.ok(new MemberResponseDto(memberId));
    }
}
