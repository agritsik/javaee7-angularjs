#!/usr/bin/env bash

docker build -t agritsik/catalog-httpd docker/_images/catalog-httpd/

docker rm -f -v catalog-httpd
docker run -d -p 80:80 --link=catalog-gf:catalog-gf --name catalog-httpd agritsik/catalog-httpd