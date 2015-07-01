#!/usr/bin/env bash

mvn clean -f catalog/pom.xml && mvn test -f catalog/pom.xml -Parquillian-wildfly