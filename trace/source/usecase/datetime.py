from time import sleep
from datetime import datetime
from opentelemetry import trace


tracer = trace.get_tracer("exchange-service.tracer")


def get_datetime():
    with tracer.start_as_current_span("exchange.get_datetime") as span:
        sleep(0.10)

        span.set_attribute("datetime", datetime.now().isoformat())
        return datetime.now().isoformat()
