FROM python:3.11

WORKDIR /app

RUN apt update && apt install -y curl 2ping iputils-ping

# display standard output
ENV PYTHONUNBUFFERED=1
# install dependencies
COPY requirements.txt /app

RUN pip3 install -r requirements.txt --no-cache-dir
RUN pip3 install pylint ipdb
RUN opentelemetry-bootstrap -a install

RUN adduser vscode
RUN groupadd docker
RUN usermod -aG docker vscode

WORKDIR /app