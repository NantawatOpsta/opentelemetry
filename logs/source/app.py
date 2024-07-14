import logging
from flask import Flask

app = Flask(__name__)
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


@app.route("/")
def home():
    logger.warning("home page warning")
    return "OK"


@app.route("/hello")
def hello():
    logger.warning("hello page warning")
    return "Hello, World!"


@app.route("/error")
def error():
    logger.error("error page")
    raise Exception("Error")
