from flask import Flask

app = Flask(__name__)


@app.route("/")
def home():
    return "OK"


@app.route("/error")
def error():
    raise Exception("Error")
