FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
ARG NOTIFICATION_API_APP_NAME
ARG NOTIFICATION_API_SERVER_PORT
ARG SERVICE_REGISTRY_APPLICATION_NAME
ARG SERVICE_REGISTRY_SERVER_PORT
ARG SMTP_MAIL_HOSTNAME
ARG SMTP_MAIL_HOST_PORT
ARG SMP_MAIL_USERNAME
ARG SMTP_MAIL_PASSWORD

ENV NOTIFICATION_API_APP_NAME=${NOTIFICATION_API_APP_NAME}
ENV NOTIFICATION_API_SERVER_PORT=${NOTIFICATION_API_SERVER_PORT}
ENV SERVICE_REGISTRY_APPLICATION_NAME=${SERVICE_REGISTRY_APPLICATION_NAME}
ENV SERVICE_REGISTRY_SERVER_PORT=${SERVICE_REGISTRY_SERVER_PORT}
ENV SMTP_MAIL_HOSTNAME=${SMTP_MAIL_HOSTNAME}
ENV SMTP_MAIL_HOST_PORT=${SMTP_MAIL_HOST_PORT}
ENV SMP_MAIL_USERNAME=${SMP_MAIL_USERNAME}
ENV SMTP_MAIL_PASSWORD=${SMTP_MAIL_PASSWORD}

WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src
RUN mvn package -DskipTest
WORKDIR /build/target/
RUN java -Djarmode=layertools -jar exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-api.jar extract

FROM maven:3.9.8-eclipse-temurin-21-alpine AS final
COPY --from=builder /build/target/dependencies/ ./
COPY --from=builder /build/target/spring-boot-loader/ ./
COPY --from=builder /build/target/snapshot-dependencies/ ./
COPY --from=builder /build/target/application/ ./

EXPOSE ${NOTIFICATION_API_SERVER_PORT}

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]