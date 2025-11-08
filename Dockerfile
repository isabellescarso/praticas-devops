FROM openjdk:17

# Set the working directory in the container
WORKDIR /praticas-devops

# Copy the JAR file into the container at /educacaoGamificada
COPY target/*.jar /praticas-devops/praticas-devops-0.0.1-SNAPSHOT.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "praticas-devops-0.0.1-SNAPSHOT.jar"]