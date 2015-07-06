#!/usr/bin/env bash

docker build -t agritsik/catalog-node docker/_images/catalog-node/

docker rm -f -v catalog-node
docker run -d -p 80:80 --link=catalog-gf:catalog-gf --name catalog-node agritsik/catalog-node