import logging
from flask import Flask

app = Flask(__name__)
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


@app.route("/")
def home():
    logger.info("Home")
    return "OK"


@app.route("/hello")
def hello():
    return "Hello, World!"


@app.route("/error")
def error():
    raise Exception("Error")
