# Set the base image to the JBoss/WildFly 27 server
FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk17

# Set the working directory in the container
WORKDIR /opt/jboss/wildfly/standalone/deployments/

# Install the dependencies and package the application
#RUN mvn clean install

# Copy your Java 17 app to the deployments directory in the container
COPY target/appointment-app.war /opt/jboss/wildfly/standalone/deployments/

# Expose the HTTP port that the app will run on
EXPOSE 8080

# Start the JBoss/WildFly server
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]