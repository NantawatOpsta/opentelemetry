package com.opstella.observability.springdemo;

import com.opstella.observability.springdemo.usecase.BannerInteractor;
import com.opstella.observability.springdemo.usecase.DatetimeInteractor;
import com.opstella.observability.springdemo.usecase.ExchangeInteractor;
import com.opstella.observability.springdemo.usecase.UserInteractor;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TraceController {
    private final OpenTelemetry openTelemetry;

    private final BannerInteractor bannerInteractor;
    private final DatetimeInteractor datetimeInteractor;
    private final ExchangeInteractor exchangeInteractor;
    private final UserInteractor userInteractor;


    @GetMapping("/")
    public ResponseEntity<?> home() {
        log.info(bannerInteractor.getHeroBanner());
        log.info(bannerInteractor.getSidebarBanner());
        log.info(System.getenv("API_URL"));

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/error")
    public ResponseEntity<?> error() {
        if (true) {
            throw new RuntimeException("An error occurred");
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/datetime")
    public ResponseEntity<?> getDatetimeInteractor() throws InterruptedException {
        return ResponseEntity.ok(datetimeInteractor.getDatetime());
    }

    @GetMapping("/exchange-rate")
    public ResponseEntity<?> getExchangeRate() throws InterruptedException {
        Tracer tracer = openTelemetry.getTracer(TraceController.class.getName());

        Span span = tracer.spanBuilder("exchange.get_datetime").startSpan();
        try (Scope ignored = span.makeCurrent()) {
            userInteractor.handleUserLogin();
            datetimeInteractor.getDatetime();
            ExchangeInteractor.ExchangeRateEntity rate = exchangeInteractor.getExchangeRate();
            return ResponseEntity.ok(rate);
        } finally {
            span.end();
        }
    }

}
