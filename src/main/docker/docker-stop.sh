#!/bin/bash

echo "Removing Docker containers and images"

if `docker ps -a | grep -q 'poc-mongodb'` ; then
  docker rm -vf poc-mongodb
fi
if `docker images | grep -q 'poc-mongodb[[:space:]]*${project.version}'` ; then
  docker rmi -f poc-mongodb:${project.version}
fi

if `docker ps -a | grep -q 'poc-app'` ; then
  docker rm -vf poc-app
fi
if `docker images | grep -q 'poc-app[[:space:]]*${project.version}'` ; then
  docker rmi -f poc-app:${project.version}
fi
