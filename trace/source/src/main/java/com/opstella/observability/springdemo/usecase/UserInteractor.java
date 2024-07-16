package com.opstella.observability.springdemo.usecase;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractor {

    @WithSpan("exchange.user_login")
    public UserEntity handleUserLogin() throws InterruptedException {
        Thread.sleep(120);
        UserEntity user = getUser();
        Span.current().setAttribute("user", user.getUser());
        Span.current().setAttribute("token", user.getToken());
        return new UserEntity("user1", "token1");
    }

    private UserEntity getUser() {
        return new UserEntity("user1", "token1");
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class UserEntity {
        private String user;
        private String token;
    }
}
