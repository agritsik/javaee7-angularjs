#!/usr/bin/env bash

# Remove all stopped containers
docker rm -v $(docker ps -a -q)

# Remove all untagged images
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")