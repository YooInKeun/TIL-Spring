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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Q: 소셜 로그인 종류 추가할 때, 모델에 type 추가하고 interface 만들기
 * Q: Cookie > JSESSIONID가 왜 계속 남지?
 * Q: 창 닫기 & 리다이렉트 과정 어떻게 할지 고민해보기
 */
@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession httpSession;
    private final UserService userService;
    private final HttpServletResponse response;

    @GetMapping(value = "/auth/google")
    public ResponseEntity<String> googleLoginRequest() {
        return new ResponseEntity<>("http://localhost:8080/oauth2/authorization/google", HttpStatus.OK);
    }

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
