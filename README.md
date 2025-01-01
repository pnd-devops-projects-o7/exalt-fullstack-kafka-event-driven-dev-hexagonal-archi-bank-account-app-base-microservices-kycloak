# Bank-Account-App [Archi hexagonale | Microservices | Spring boot 3 | Spring Security | Kafka |  KeyCloak]
- **Bank-Account-App** est application fullstack (Java /Angular) orientée microservices: des microservices **business** et des  microservices **transverses**.
    - la communication entre les microservices est **synchrone** avec **OpenFeign** de Spring Cloud.
- Chaque business microservice implémente une **architecture hexagonale**, des tests unitaires et d'intération pour garantir la qualité du code source.
    - **SonarQube** check les vulnérabilités, le code smell, la couverture de code et la sécurité
- Mise en place d'une **infrastructure Kafka**:
    - pour persister et distribuer les événements liés à la modification des données dans les BDD.
- **KeyCloak** Identity Provider (IDP) est utilisé pour authifier et autoriser l'accès au resources server (Backend microservice)
    - utilisation d'un **JWT** géré par **KeyCloak**
- Une **application frontend** en Angular 16.
- Un workflow avec Jenkins pour automatiser les jobs: 
    - builder chaque microservice ***mvn clean install***,
    - pour lancer les tests unitaires et d'intération ***mvn test***, 
    - builder l'image docker de chaque microservice ***docker compose -f stak.yml build***, 
    - publier les images into dockerhub ***docker compose -f stack.yml push***.

## Bank-Account-App architecture
L'architecture de **Bank-Account-App**
![application-architecture](diagrams/exalt-bank-account-app-archi.gif)

## Bank-Account-App conceptual model
Modèle conceptuel de l'application **Bank-Account-App**
![conceptual-model](diagrams/exalt-account-conception.jpg)

## Authentication & authorization flows to backend resources
Before connecting the frontend application (Angular App), a **Backend-Gateway-OAuth2-Client** is configured as **trusted client** in KeyCloak.  
Refering the flow below
- Backend-Gateway-OAuth2-Client serves as **TokenRelay** between **KeyCloak** IDP and **Backend resources servers**
-  and routes authenticated user's requests (postman) to Backend resources servers. 
![flows1](diagrams/exalt-bank-account-app-flows-1.gif)

After connecting the **frontend-App** to the **Backend Microservices**, refering the flow below: 
- all the configurations are moved on frontend side and are removed on Backend-Gateway-OAuth2-Client side
- The Backend-Gateway-OAuth2-Client becomes a simple gateway to route any request without any check
    - it is no longer a TokenRelay
- Frontend App is configured as **trusted** in KeyCloak, refer to flow below:
![flows2](diagrams/exalt-bank-account-app-flows-2.gif)

# Backend
La partie backend comprend:

## 4 applications microservices business:
 chaque business microservice implémentent une architecture hexagonale
- ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer-api```
    - managing customers, 
    - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
- ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account-api```
    - managingg bank-accounts
    - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
- ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation-api```,
    - managing operations on bank-accounts
    - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
- ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-api```
    lorsqu'un business microservice: customer-api, bank-account-api, ou operation-api produit un événement dans un kafka topic:  
    - ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-api``` envoi un email de notification vers un **smtp-mail-notification-server** (MailHog docker container)
    - cette api n'expose donc pas d'endpoint

- Bank-Account-App étant orientée microservices, chaque business microservice utilse sa propre base de données **MySql** pour la persister les données

## 1 api microservice transverse**: 
- ```exalt-hexagonal-archi-kafka-keycloak-backend-gateway-oauth2-client```   route users' requests to backend microservices

## infrastructure kafka
infrastructure kafka pour la persistance et la distribution des événements
- un zookeeper-server 
- 2 kafka-servers
- un schema-registry-service
    - pour definir le modèle commun de message pour le **producer** et le **consumer**
    - utilise **avro** pour définir ce modèle
- un kafka-ui pour interface graphique d'observabilité des flows dans kafka infrastructure

## Bank-Account-App deployment
Tout l'ecosystème des applications de **Bank-Account-App** est containeurisé avec **docker** et déployés ensuite dans un cluster locale **Minikuke** avec **Kubernetes**
- **docker engine** pour construire les images docker de chaque business microservice et transverse microservice
- **docker compose** pour prépaper la stack de déploiement dans des docker containers 
- cluster local **Minikuke** ensuite dans le cluster GCP avec **GKE**: Google Kubernetes engine

# Partie Frontend
La partie frontend de **Bank-Account-App**: une application développée en angular 16:
- Pattern observable avec **RxJs**
- Gestion observable liés aux événements
- Design graphique avec **PrimeNG**
