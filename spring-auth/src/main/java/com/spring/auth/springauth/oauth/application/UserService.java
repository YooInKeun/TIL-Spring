package com.spring.auth.springauth.oauth.application;

import com.spring.auth.springauth.oauth.domain.User;
import com.spring.auth.springauth.oauth.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("유저를 찾을 수 없음"));
    }
}
