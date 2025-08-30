package com.timemanage.gigatomson.auth.service;

import com.timemanage.gigatomson.auth.dto.KakaoUserResponse;
import com.timemanage.gigatomson.auth.jwt.JwtProvider;
import com.timemanage.gigatomson.auth.kakao.KakaoOAuthClient;
import com.timemanage.gigatomson.user.domain.KakaoUser;
import com.timemanage.gigatomson.user.repository.KakaoUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final KakaoUserRepository KakaoUserRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public AuthResult authenticateByCode(String code) {
        // 1) code → access_token 교환
        var token = kakaoOAuthClient.exchangeCodeForToken(code);

        // 2) access_token → 유저 정보 조회
        KakaoUserResponse me = kakaoOAuthClient.getUserInfo(token.getAccessToken());
        Long kakaoId = me.getId();
        String nickname = (me.getKakaoAccount() != null && me.getKakaoAccount().getProfile() != null)
                ? me.getKakaoAccount().getProfile().getNickname()
                : "카카오유저";

        // 3) DB upsert
        var user = KakaoUserRepository.findByKakaoId(kakaoId)
                .map(u -> { u.setNickname(nickname); return u; })
                .orElseGet(() -> KakaoUser.builder()
                        .kakaoId(kakaoId)
                        .nickname(nickname)
                        .build());
        KakaoUserRepository.save(user);

        // 4) JWT 발급
        String jwt = jwtProvider.createAccessToken(user.getId());

        return new AuthResult(jwt, user.getId(), user.getNickname());
    }

    public record AuthResult(String accessToken, Long userId, String nickname) {}
}
