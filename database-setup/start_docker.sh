#!/usr/bin/env bash

image_version=8
container_name=mysql
password=mysql
database=example

docker container run --rm -d -e MYSQL_ROOT_PASSWORD=${password} -e MYSQL_DATABASE=${database} -p 3306:3306 --name ${mysql} mysql:${image_version}
