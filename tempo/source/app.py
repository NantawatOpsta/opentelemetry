import requests

from flask import Flask
# from opentelemetry import trace
from usecase import datetime, user

# Acquire a tracer
# tracer = trace.get_tracer("diceroller.tracer")

app = Flask(__name__)


@app.route("/")
def home():
    user.handle_user_login()
    requests.get('http://localhost:8080/datetime')
    return "OK"


@app.route("/error")
def error():
    raise Exception("An error occurred")


@app.route("/datetime")
def get_datetime():
    return datetime.get_datetime()
