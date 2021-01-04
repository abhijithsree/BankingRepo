FROM openjdk:8

EXPOSE 8090

ADD target/bank-gateway.jar bank-gateway.jar

ENTRYPOINT ["java","-jar","bank-gateway.jar"]