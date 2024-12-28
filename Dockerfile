FROM openjdk
WORKDIR /app
COPY target/Apni-Dukkan.jar .

EXPOSE 8081
CMD ["java","-jar","Apni-Dukkan.jar"]
