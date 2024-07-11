import requests

from flask import Flask
from opentelemetry import trace
from usecase import datetime, user, exchange, banner

# Acquire a tracer
tracer = trace.get_tracer("exchange-service.tracer")

app = Flask(__name__)


@app.route("/")
def home():
    banner.get_hero_banner()
    banner.get_sidebar_banner()
    requests.get('https://google.com')
    return "OK"


@app.route("/error")
def error():
    raise Exception("An error occurred")


@app.route("/datetime")
def get_datetime():
    return datetime.get_datetime()


@app.route("/exchange-rate")
def get_exchange_rate():
    with tracer.start_as_current_span("exchange.get_exchange_rate"):
        user.handle_user_login()
        datetime.get_datetime()
        rate = exchange.get_exchange_rate()
        return rate
