package com.timemanage.gigatomson.auth.controller;

import com.timemanage.gigatomson.auth.service.KakaoAuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
public class AuthController {

    private final KakaoAuthService kakaoAuthService;

    /**
     * 프런트가 카카오 인가코드(code)를 쿼리/바디로 전달.
     * ex) POST /api/auth/kakao/exchange?code=XXXXX
     */
    @PostMapping("/exchange")
    public ResponseEntity<AuthResponse> exchange(@RequestParam("code") String code) {
        var result = kakaoAuthService.authenticateByCode(code);
        var resp = new AuthResponse(result.accessToken(), result.userId(), result.nickname());
        return ResponseEntity.ok(resp);
    }

    @Data
    public static class AuthResponse {
        private final String accessToken;
        private final Long userId;
        private final String nickname;
    }
}