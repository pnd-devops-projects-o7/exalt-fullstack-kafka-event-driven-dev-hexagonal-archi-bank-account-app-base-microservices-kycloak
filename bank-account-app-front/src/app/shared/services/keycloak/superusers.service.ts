import { inject, Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class SuperusersService {
  keycloakService = inject(KeycloakService);

  isSuperUser(): boolean {
    const superRoles: Array<string> = ["ADMIN","OWNER","MANAGER"];
    return this.keycloakService.getUserRoles().some((role: string)=>superRoles.includes(role))
  }
}
