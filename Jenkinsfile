pipeline {
    agent any
    tools {
        maven "Maven-3.9.6"
        jdk "Java-21"
    }
    stages {
        stage("Checkout") {
            steps {
                checkout scmGit(
                        branches: [[name: "*/main"]],
                        extensions: [],
                        userRemoteConfigs: [[url: "https://github.com/pnd-devops-projects-o7/exalt-fullstack-kafka-event-driven-dev-hexagonal-archi-bank-account-app-base-microservices-kycloak.git"]])
            }
        }
        stage("Build") {
            steps {
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-backend-gateway-oauth2-client/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation-api/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-api/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer-api/") {
                    sh "mvn clean install"
                }
                dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account-api/") {
                    sh "mvn clean install"
                }
            }
            post {
                success {
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-eureka-server/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-backend-gateway-oauth2-client/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation-api/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-api/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer-api/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                    dir("./bank-account-app-back/exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account-api/") {
                        archiveArtifacts "**/target/*.jar"
                    }
                }
            }
        }
        stage("Docker img Build") {
            steps {
                script {
                    withDockerRegistry([credentialsId: "dockerhub-credentials-jenkins", url: ""]) {
                        sh "docker compose -f ./docker/bank-account-app-compose.yml build"
                        sh "docker system prune -f"
                    }
                }
            }
        }
        stage("Publish docker images") {
            steps {
                script {
                    withDockerRegistry([credentialsId: "dockerhub-credentials-jenkins", url: ""]) {
                        sh "docker compose -f ./docker/bank-account-app-compose.yml push"
                    }
                }
            }
        }
    }
}