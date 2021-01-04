FROM openjdk:8

EXPOSE 8095

ADD target/bank-balance.jar bank-balance.jar

ENTRYPOINT ["java","-jar","bank-balance.jar"]