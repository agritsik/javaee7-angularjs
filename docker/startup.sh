#!/usr/bin/env bash

sh docker/server/run-package.sh
sh docker/server/run-db.sh
sh docker/server/run-gf.sh

sh docker/client/run-build.sh
sh docker/client/run-nginx.sh

docker ps

