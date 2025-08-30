package com.timemanage.gigatomson.auth.kakao;

import com.timemanage.gigatomson.auth.dto.KakaoTokenResponse;
import com.timemanage.gigatomson.auth.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient {

    private final WebClient webClient = WebClient.builder().build();

    @Value("${oauth.kakao.token-uri}")
    private String tokenUri;
    @Value("${oauth.kakao.userinfo-uri}")
    private String userInfoUri;

    @Value("${oauth.kakao.client-id}")
    private String clientId;
    @Value("${oauth.kakao.client-secret:}")
    private String clientSecret;
    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;

    public KakaoTokenResponse exchangeCodeForToken(String code) {
        var form = new LinkedMultiValueMap<String, String>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", clientId);
        if (clientSecret != null && !clientSecret.isBlank()) {
            form.add("client_secret", clientSecret);
        }
        form.add("redirect_uri", redirectUri);
        form.add("code", code);

        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(form))
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .block();
    }

    public KakaoUserResponse getUserInfo(String accessToken) {
        return webClient.get()
                .uri(userInfoUri)
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserResponse.class)
                .block();
    }
}