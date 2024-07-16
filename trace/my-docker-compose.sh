#!/usr/bin/env bash

if [ -z "`which docker`" ]; then
  echo "install docker first"
  exit 1
fi

if [ -z "`which mvn`" ]; then
  echo "install mvn first"
  exit 1
fi


DIR_PATH=$(cd `dirname $0`; pwd)

cd $DIR_PATH/source && mvn clean install

cd $DIR_PATH && docker compose down; docker compose up -d

docker ps
