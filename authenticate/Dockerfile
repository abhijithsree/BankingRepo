FROM openjdk:8

EXPOSE 8092

ADD target/bank-user-auth.jar bank-user-auth.jar

ENTRYPOINT ["java","-jar","bank-user-auth.jar"]