from flask import Flask
from datetime import datetime
# from opentelemetry import trace
import requests

# Acquire a tracer
# tracer = trace.get_tracer("diceroller.tracer")

app = Flask(__name__)


@app.route("/")
def home():
    response = requests.get('http://localhost:8080/datetime')
    print(response.status_code)
    return "OK"


@app.route("/datetime")
def get_datetime():
    return datetime.now().isoformat()
