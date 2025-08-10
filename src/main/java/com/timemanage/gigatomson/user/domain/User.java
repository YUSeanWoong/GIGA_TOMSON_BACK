package com.timemanage.gigatomson.user.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_login")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private int idx;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_ver")
    private String userVer;

    @Column(name = "log_auto")
    private int logAuto;

    @Column(name = "kakao_token")
    private String kakaoToken;

    @Column(name = "join_ym")
    private int joinYm;

    @Column(name = "btn_login_kakao")
    private int btnLoginKakao;

    @Column(name = "btn_find_id")
    private int btnFindId;

    @Column(name = "btn_find_pw")
    private int btnFindPw;

    @Column(name = "btn_signup")
    private int btnSignup;

    @Column(name = "text_version")
    private String textVersion;

    @Column(name = "text_title")
    private String textTitle;

    @Column(name = "img_title")
    private String imgTitle;
}
