package com.timemanage.gigatomson;


import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        String activeProfile = System.getenv().getOrDefault("SPRING_PROFILES_ACTIVE", System.getProperty("spring.profiles.active", "default"));
        System.out.println("############");
        System.out.println(activeProfile);
        System.out.println("############");
        if("local".equalsIgnoreCase(activeProfile)) {
            Dotenv env = Dotenv.configure().ignoreIfMissing().load();
            System.setProperty("DB_URL", env.get("DB_URL", ""));
            System.setProperty("DB_USERNAME", env.get("DB_USERNAME", ""));
            System.setProperty("DB_PASSWORD", env.get("DB_PASSWORD", ""));
            System.setProperty("KAKAO_REST_API_KEY", env.get("KAKAO_REST_API_KEY", ""));
            System.setProperty("JWT_SECRET", env.get("JWT_SECRET", ""));
            System.setProperty("JWT_ISSUER", env.get("JWT_ISSUER", ""));
            System.out.println("############");
            System.out.println(env.get("KAKAO_REST_API_KEY", ""));
            System.out.println("############");
        }
        SpringApplication.run(Main.class, args);
    }

}