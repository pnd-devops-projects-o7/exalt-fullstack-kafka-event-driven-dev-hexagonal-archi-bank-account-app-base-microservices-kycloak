// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.

import { KeycloakOptions } from "keycloak-angular";

// The list of file replacements can be found in `angular.json`.
const keycloakInitOptions: KeycloakOptions= {
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

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
