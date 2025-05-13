FROM bellsoft/liberica-openjdk-alpine
# Instal curl and jq in linux container
RUN apk add curl jq
# Creating work directory
WORKDIR /demo-docker-project-directory

ADD target/docker-resources/libs                      libs
ADD target/docker-resources/demo.properties           demo.properties
ADD target/docker-resources/healthcheck.sh            healthcheck.sh
ADD target/docker-resources/pom.xml                   pom.xml
ADD target/docker-resources/testng.xml                testng.xml

ENTRYPOINT sh healthcheck.sh




