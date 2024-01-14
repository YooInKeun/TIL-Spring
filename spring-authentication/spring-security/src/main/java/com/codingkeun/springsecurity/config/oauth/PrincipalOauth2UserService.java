package com.codingkeun.springsecurity.config.oauth;

import com.codingkeun.springsecurity.config.auth.PrincipalDetails;
import com.codingkeun.springsecurity.config.oauth.provider.FacebookUserInfo;
import com.codingkeun.springsecurity.config.oauth.provider.GoogleUserInfo;
import com.codingkeun.springsecurity.config.oauth.provider.NaverUserInfo;
import com.codingkeun.springsecurity.config.oauth.provider.OAuth2UserInfo;
import com.codingkeun.springsecurity.model.User;
import com.codingkeun.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if ("google".equals(registrationId)) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if ("facebook".equals(registrationId)) {
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if ("naver".equals(registrationId)) {
            System.out.println("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        } else {
            System.out.println("구글, 페이스북, 네이버 로그인만 지원합니다.");
        }

        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = "null";
        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String role = "ROLE_USER";

        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();

            userRepository.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
