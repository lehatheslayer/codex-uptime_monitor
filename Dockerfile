FROM maven:3.8.2-jdk-11
COPY . /myapp
WORKDIR /myapp
CMD mvn spring-boot:run -Dspring-boot.run.arguments="--status=running"
