from time import sleep
from opentelemetry import trace


tracer = trace.get_tracer("exchange-service.tracer")


def get_exchange_rate():
    with tracer.start_as_current_span("exchange.get_exchange_rate") as span:
        sleep(0.11)

        exchange_rate = 1.0
        span.set_attribute("exchange_rate", exchange_rate)
        # set status completed
        span.set_status(trace.Status(trace.StatusCode.OK))
        return {"exchange_rate": exchange_rate}
