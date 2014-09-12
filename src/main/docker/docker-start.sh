#!/bin/bash

WORKING_DIR=$(dirname $0)
STARTUP_TIMEOUT=30
STARTUP_TIME=0

sh $WORKING_DIR/docker-stop.sh

echo "Building Docker images..."
docker build --force-rm=true -t poc-mongodb:${project.version} $WORKING_DIR/mongodb
docker build --force-rm=true -t poc-app:${project.version} $WORKING_DIR/app

echo "Running Docker container poc-mongodb..."
docker run -d --name poc-mongodb poc-mongodb:${project.version} --smallfiles
# Wait for poc-mongodb startup
while [ $STARTUP_TIME -le $STARTUP_TIMEOUT ]; do
    echo -n "."
    STARTUP_TIME=$(( $STARTUP_TIME + 1 ))
    if `docker logs poc-mongodb | grep -q 'waiting for connections'` ; then
      break
    fi
    sleep 1
done

echo -e "\nRunning Docker container poc-app..."
docker run -d -p 8080:8080 --name poc-app --link poc-mongodb:poc-mongodb poc-app:${project.version}
# Wait for poc-app startup
while [ $STARTUP_TIME -le $STARTUP_TIMEOUT ]; do
    echo -n "."
    STARTUP_TIME=$(( $STARTUP_TIME + 1 ))
    if `docker logs poc-app | grep -q 'org.eclipse.jetty.server.Server - Started'` ; then
      break
    fi
    sleep 1
done

if [ $STARTUP_TIME -le $STARTUP_TIMEOUT ]; then
  echo -e "\nDocker environment is up!" && exit 0
else
  echo -e "\nTimeout!" && exit 1
fi
