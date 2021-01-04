FROM openjdk:8

EXPOSE 8094

ADD target/bank-transaction.jar bank-transaction.jar

ENTRYPOINT ["java","-jar","bank-transaction.jar"]