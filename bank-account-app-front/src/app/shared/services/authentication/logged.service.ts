import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class LoggedService {

  constructor(private keycloakService: KeycloakService) { }

  async isLoggedPromise(): Promise<boolean> {
    const token = this.keycloakService.getToken();
    if(await token){
      return Promise.resolve(true);
    }
    return Promise.resolve(false);
  }
}

