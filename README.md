# Bank-Account-App [Micro services | Spring boot 3 | Spring cloud | Kafka]
- **Bank-Account-App** est application fullstack (Java 21 /Angular 16) orientée microservices composée des microservices **métiers** et des  microservices **transverses**.
    - la communication entre les microservices se fait de manière **asynchrone** via une **infrastructure Kafka**
- Chaque microservice métier est implémenté dans une **architecture hexagonale**, des tests unitaires sont écrits pour garantir la qualité du code source.
    - **SonarQube** est utilisé pour checker les vulnérabilités, le code smell, la couverture de code et la sécurité
- Une **infrastructure Kafka** est mise en place:
    - pour persister et distribuer les événements liés à la modification des données dans la BDD.
    - assurer une communication asynchrone entre les microservices
- Un **KeyCloak authentication provider** est utilisé et la sécurité des ressources des backends (microservices) est implémentée.
    - utilisation d'un **JWT** géré par **KeyCloak**
- Une **application frontend** en Angular 16 pour servir de ui aux utilisateurs.
- mise en place d'un workflow avec Jenkins pour automatiser les jobs: ***build***, ***test***, ***docker build***, ***docker push***.

## Bank-Account-App architecture
L'architecture globale de l'application **Bank-Account-App**
![application-architecture](diagrams/Peek%202024-12-13%2013-41.gif)

## Bank-Account-App conceptual model
Modèle global conceptuel de l'application **Bank-Account-App**
![conceptual-model](diagrams/exalt-account-conception.jpg)

## Authentication & authorization flows to backend resources
![resources-authentication](diagrams/authentication-authorization-flow.png)
- for client, we use ***postman*** and an ***Angular application***
- for authentication & authorization server we use ***KeyCloak***
- backend resources is a ***Bank-Account-App*** base microservices

# Partie Backend
La partie backend de comprend:

 - **4 applications microservices métiers**:
    - ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-customer```
        - managing customers, 
        - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
    - ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-bank-account```
        - managingg bank-accounts
        - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
    - ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-operation```,
        - managing operations on bank-accounts
        - oauth2 authentication & authorization est implémenté pour authentifier toutes les requêtes vers ce backend
    - ```exalt-hexagonal-archi-kafka-keycloak-bs-ms-notification-service```
        - envoie une notification par mail lorsqu'une operation d'écriture se produit sur un customer, un compte ou une operation 

- chaque microservice métier utilse sa propre base de données MySql pour la persistance les data

- **1 api microservice transverse**: 
    - ```exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy```  
    - la ***exalt-hexagonal-archi-kafka-keycloak-gateway-service-proxy*** route toutes les requêtes vers le backend.
    - une forme de ***résilience*** est implémentée dans la ***gateway-service-proxy*** lorsque le backend demandé n'est pas disponible.

- **infrastructure kafka**: pour la persistance et la distribution des événements kafka
    - un zookeeper-server 
    - 2 kafka-servers
    - un schema-registry-service
        - pour definir le modèle commun de message pour le **producer** et le **consumer**
        - utilise **avro** pour définir ce modèle
    - un kafka-UI

- Tout l'ecosystème des applications de **Bank-Account-App** est containeurisé avec **docker** et déployés ensuite dans un cluster locale **Minikuke** avec **Kubernetes**
    - **docker engine** pour construire les images docker pour chaque service
    - **docker compose** pour prépaper le le déploiement de la stack des containers 
    - cluster local **Minikuke** ensuite dans le cluster GCP avec **GKE**: Google Kubernetes engine

# Partie Frontend

La partie frontend de **Bank-Account-App **** est une application développée en angular 16**:
- Pattern observable avec **RxJs**
- Gestion observable liés aux événements de click
- Design graphique avec **PrimeNG**
