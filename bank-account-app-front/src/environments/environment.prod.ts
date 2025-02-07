import { KeycloakOptions } from "keycloak-angular";

// The list of file replacements can be found in `angular.json`.
const keycloakInitOptions: KeycloakOptions = {
    config: {
        url: 'http://my.keycloak-auth-provider.org:8380',
        realm: 'pnd_realm',
        clientId: 'exalt-hexagonal-archi-kafka-keycloak-frontend-app'
    },
    enableBearerInterceptor: true,
    bearerPrefix: 'Bearer',
    bearerExcludedUrls: ['/assets'],
    initOptions: {
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri:
            window.location.origin + '/assets/silent-check-sso.html'
    }
}

export const environment = {
    production: true,
    gatewayApiService: 'http://localhost:8279/api',
    logoutUrl: 'http://exalt-hexagonal-archi-kafka-keycloak-frontend-app:4200',
    keycloackConfInit: keycloakInitOptions
}