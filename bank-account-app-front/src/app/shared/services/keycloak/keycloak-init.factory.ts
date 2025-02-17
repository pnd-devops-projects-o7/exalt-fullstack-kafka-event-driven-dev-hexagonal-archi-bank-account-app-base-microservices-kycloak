import { KeycloakService } from "keycloak-angular";
import { environment } from "../../../../environments/environment.prod";

// initializing keycloak factory

export function initializeKeycloak(keycloak: KeycloakService): () => Promise<any> {
  return () =>
    keycloak.init(environment.keycloackConfInit);
}