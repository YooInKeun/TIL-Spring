package com.spring.auth.springauth.session.domain;

public interface PasswordEncryptor {
    String encrypt(String plain);

    boolean match(String password, String plain);
}
