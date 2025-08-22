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
    private Integer idx;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_ver")
    private String userVer;

    @Column(name = "log_auto")
    private Integer logAuto;

    @Column(name = "kakao_token")
    private String kakaoToken;

    @Column(name = "join_ym")
    private Integer joinYm;

    @Column(name = "btn_login_kakao")
    private Integer btnLoginKakao;

    @Column(name = "btn_find_id")
    private Integer btnFindId;

    @Column(name = "btn_find_pw")
    private Integer btnFindPw;

    @Column(name = "btn_signup")
    private Integer btnSignup;

    @Column(name = "text_version")
    private String textVersion;

    @Column(name = "text_title")
    private String textTitle;

    @Column(name = "img_title")
    private String imgTitle;


}
