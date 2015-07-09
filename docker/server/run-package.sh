#!/usr/bin/env bash

mvn clean -f catalog/pom.xml && mvn package -f catalog/pom.xml -Pdocker-dev

cp catalog/target/catalog-1.0-SNAPSHOT.war docker/_images/catalog-gf/target/app.war
