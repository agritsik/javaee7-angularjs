#!/usr/bin/env bash

# build server
sh docker/server/run-package.sh
sh docker/server/run-db.sh
sh docker/server/run-gf.sh

# build client
sh docker/client/run-build.sh
sh docker/client/run-nginx.sh

docker ps

