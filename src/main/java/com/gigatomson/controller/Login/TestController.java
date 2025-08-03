package com.gigatomson.controller.Login;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "CI/CD 성공 2025/08/03 tlqkf... 7시간걸렸다...";
    }

}
