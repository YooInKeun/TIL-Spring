package com.spring.auth.springauth.member.domain;

public interface PasswordEncryptor {
    String encrypt(String plain);

    boolean match(String password, String plain);
}
