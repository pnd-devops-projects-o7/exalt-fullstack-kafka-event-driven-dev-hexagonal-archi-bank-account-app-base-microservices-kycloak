FROM maven:3.9.8-eclipse-temurin-21-alpine AS builder
ARG SERVICE_REGISTRY_API_APP_NAME
ARG SERVICE_REGISTRY_API_SERVER_PORT

ENV SERVICE_REGISTRY_API_APP_NAME=${SERVICE_REGISTRY_API_APP_NAME}
ENV SERVICE_REGISTRY_API_SERVER_PORT=${SERVICE_REGISTRY_API_SERVER_PORT}

WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src
RUN mvn package -DskipTest
WORKDIR /build/target/
RUN java -Djarmode=layertools -jar exalt-hexagonal-archi-kafka-keycloak-registry-eureka-server.jar extract

FROM maven:3.9.8-eclipse-temurin-21-alpine AS final
COPY --from=builder /build/target/dependencies/ ./
COPY --from=builder /build/target/spring-boot-loader/ ./
COPY --from=builder /build/target/snapshot-dependencies/ ./
COPY --from=builder /build/target/application/ ./

EXPOSE ${SERVICE_REGISTRY_API_SERVER_PORT}

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]