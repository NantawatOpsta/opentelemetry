from time import sleep
from opentelemetry import trace


tracer = trace.get_tracer("exchange-service.tracer")


def handle_user_login():
    with tracer.start_as_current_span("exchange.user_login") as span:
        sleep(0.12)

        user = get_user()
        span.set_attribute("user", user["user"])
        span.set_attribute("token", user["token"])
        return {"user": "user1", "token": "token1"}


def get_user():
    return {"user": "user1", "token": "token1"}
