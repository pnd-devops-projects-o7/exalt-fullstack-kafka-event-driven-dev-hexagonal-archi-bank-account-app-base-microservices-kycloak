import { KeycloakOptions } from "keycloak-angular";

// The list of file replacements can be found in `angular.json`.
const keycloakInitOptions: KeycloakOptions = {
    config: {
        url: 'http://localhost:8380',
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
    production: false,
    gatewayApiService: "http://localhost:8279/api",
    logoutUrl: "http://localhost:4200",
    keycloackConfInit: keycloakInitOptions
};
