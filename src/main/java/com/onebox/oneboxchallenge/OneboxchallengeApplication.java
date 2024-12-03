package com.onebox.oneboxchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OneboxchallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneboxchallengeApplication.class, args);
    }

}
