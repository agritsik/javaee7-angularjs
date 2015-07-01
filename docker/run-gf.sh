#!/usr/bin/env bash

docker build -t agritsik/catalog-gf docker/catalog-gf/

docker rm -f -v catalog-gf
docker run -d -p 4848:4848 -p 8080:8080 --link=catalog-db:catalog-db --name catalog-gf agritsik/catalog-gf

#docker logs -f catalog-gf