FROM --platform=linux/amd64 openjdk:13-jdk-alpine
RUN java -version

COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp/
RUN apk --no-cache add maven && mvn --version
RUN mvn package

ENTRYPOINT ["java", "-jar", "./target/processor-1.0-SNAPSHOT.jar"]