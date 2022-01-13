package com.spring.auth.springauth.session.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String passwordHash;

    public Member(String email, String name, String passwordHash) {
        this.email = email;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public void checkEqualPassword(String plainPassword, PasswordEncryptor passwordEncryptor) {
        if (!passwordEncryptor.match(passwordHash, plainPassword)) {
            throw LoginFailedException.wrongPassword();
        }
    }
}
