package com.timemanage.gigatomson.user.repository;

import com.timemanage.gigatomson.user.domain.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
        Optional<KakaoUser> findByKakaoId(Long kakaoId);
}

