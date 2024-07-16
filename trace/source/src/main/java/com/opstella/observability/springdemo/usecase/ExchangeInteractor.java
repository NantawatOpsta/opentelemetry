package com.opstella.observability.springdemo.usecase;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeInteractor {

    @WithSpan("exchange.get_exchange_rate")
    public ExchangeRateEntity getExchangeRate() throws InterruptedException {
        Thread.sleep(110);
        double exchangeRate = 1.0;
        Span.current().setAttribute("exchange_rate", exchangeRate);
        Span.current().setStatus(StatusCode.OK);
        return new ExchangeRateEntity(exchangeRate);
    }

    @AllArgsConstructor
    @Getter
    @ToString
    public static class ExchangeRateEntity {
        private double exchangeRate;
    }
}
