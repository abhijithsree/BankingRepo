FROM java:8

EXPOSE 8091

ADD target/user.jar user.jar

ENTRYPOINT ["jar","-jar","user.jar"]