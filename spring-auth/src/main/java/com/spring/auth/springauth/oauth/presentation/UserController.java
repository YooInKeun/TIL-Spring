package com.spring.auth.springauth.oauth.presentation;

import com.spring.auth.springauth.oauth.application.UserService;
import com.spring.auth.springauth.oauth.dto.SessionUser;
import com.spring.auth.springauth.oauth.config.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * TODO
 * 프론트랑 연동한다 생각하고 url 설계 및 설정
 * 로그아웃을 엔드포인트 안 파고, 걍 잘 되게 하기
 * 소셜 로그인이 로그인할 때, 유저가 아니면 회원가입해주고 아니면 로그인? 그런듯
 * 미리도서관 연동해서 써보기
 * 유저 모델에 어떤 소셜 로그인 타입인지 추가하기
 */

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getUserInfo(@LoginUser SessionUser sessionUser) {
        if (sessionUser == null) {
            System.out.println("비로그인 상태");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        System.out.println(userService.findUserById(sessionUser.getId()).getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/logout-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout() {
        httpSession.invalidate();
        return new ResponseEntity<>("suceess", HttpStatus.OK);
    }
}
