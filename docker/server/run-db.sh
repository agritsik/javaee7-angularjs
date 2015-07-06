#!/usr/bin/env bash

docker rm -f -v catalog-db

docker run -d -p 3306:3306 \
        -e MYSQL_ROOT_PASSWORD=root1 \
        -e MYSQL_USER=myuser \
        -e MYSQL_PASSWORD=mypass \
        -e MYSQL_DATABASE=catalog \
        --name catalog-db \
        mysql