package com.spring.auth.springauth.oauth.presentation;

import com.spring.auth.springauth.oauth.dto.SessionUser;
import com.spring.auth.springauth.session.infrastructure.config.CurrentLoginMemberArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute(CurrentLoginMemberArgumentResolver.LOGIN_ATTRIBUTE_NAME);
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
}
