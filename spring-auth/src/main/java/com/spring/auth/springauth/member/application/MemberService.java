package com.spring.auth.springauth.member.application;

import com.spring.auth.springauth.member.domain.LoginFailedException;
import com.spring.auth.springauth.member.domain.Member;
import com.spring.auth.springauth.member.domain.MemberRepository;
import com.spring.auth.springauth.member.domain.PasswordEncryptor;
import com.spring.auth.springauth.member.dto.LoginMember;
import com.spring.auth.springauth.member.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncryptor passwordEncryptor;

    @Transactional
    public Long create(MemberRequestDto memberRequestDto) {
        Member persistMember = memberRepository.save(createMember(memberRequestDto.getEmail(), memberRequestDto.getName(), memberRequestDto.getPassword(), passwordEncryptor));
        return persistMember.getId();
    }

    @Transactional(readOnly = true)
    public LoginMember login(String email, String plainPassword) {
        final Member findMember = memberRepository.findByEmail(email).orElseThrow(LoginFailedException::noSuchMember);
        findMember.checkEqualPassword(plainPassword, passwordEncryptor);
        return new LoginMember(findMember.getId());
    }

    public static Member createMember(String email, String name, String plainPassword, PasswordEncryptor passwordEncryptor) {
        final String passwordHash = passwordEncryptor.encrypt(plainPassword);
        return new Member(email, name, passwordHash);
    }
}
