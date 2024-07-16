package com.opstella.observability.springdemo.usecase;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DatetimeInteractor {
    private final Tracer tracer;

    @Autowired
    public DatetimeInteractor(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer(DatetimeInteractor.class.getName());
    }

    public String getDatetime() throws InterruptedException {
        Span span = tracer.spanBuilder("exchange.get_datetime").startSpan();
        try (Scope ignored = span.makeCurrent()) {
            Thread.sleep(100);

            String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            Span.current().setAttribute("datetime", now);

            return ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        } finally {
            span.end();
        }

    }

}
