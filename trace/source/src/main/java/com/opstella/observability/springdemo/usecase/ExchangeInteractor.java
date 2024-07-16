package com.opstella.observability.springdemo.usecase;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExchangeInteractor {
    private final RestTemplate restTemplate;

    @Value("${httpservice.endpoint}")
    private String httpServiceEndpoint;

    @WithSpan("exchange.get_exchange_rate")
    public ExchangeRateEntity getExchangeRate() throws InterruptedException {
        Thread.sleep(110);
        double exchangeRate = 1.0;
        Span.current().setAttribute("exchange_rate", exchangeRate);
        Span.current().setStatus(StatusCode.OK);
        callApi();
        return new ExchangeRateEntity(exchangeRate);
    }

    @WithSpan("exchange.call_api")
    String callApi() {
        return this.restTemplate.getForObject(httpServiceEndpoint, String.class);
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class ExchangeRateEntity {
        private double exchangeRate;
    }
}
