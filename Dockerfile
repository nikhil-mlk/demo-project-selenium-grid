FROM bellsoft/liberica-openjdk-alpine
# Instal curl and jq in linux container
RUN apk add curl jq

WORKDIR /demo-Docker-Project-Directory

ADD target/docker-resources ./
ADD healthcheck.sh   healthcheck.sh
ENTRYPOINT sh healthcheck.sh




