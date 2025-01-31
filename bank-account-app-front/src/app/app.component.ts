import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { MenuItem, PrimeIcons } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { KeycloakProfile } from 'keycloak-js';
import { KeycloakService } from 'keycloak-angular';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { SuperusersService } from './shared/services/keycloak/superusers.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule, CommonModule, ButtonModule, MenubarModule,
    InputGroupModule, InputGroupAddonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'bank-account-app-front';
  accueilMenu!: MenuItem[];
  router = inject(Router);
  userProfile!: KeycloakProfile;
  keyclaokService = inject(KeycloakService);
  isSuperuserService = inject(SuperusersService);


  tooltipPosition: string = "top";

  async ngOnInit(): Promise<void> {
    this.accueilMenu = [
      {

        label: "standlone", icon: PrimeIcons.HOME, 
        command: () => this.router.navigate(['standlone'])
      },
      {
        label: "app-desc", icon: PrimeIcons.INFO_CIRCLE,
        command: () => this.router.navigate(["accueil"])
      },
      {
        label: 'app', icon: PrimeIcons.ANGLE_DOUBLE_RIGHT,
        command: () => this.router.navigate(['/app-menu']),
        visible: this.isSuperuserService.isSuperUser()
      }
    ];

    if (this.keyclaokService.isLoggedIn()) {
      this.keyclaokService.loadUserProfile().then(connectedUserProfile => {
        this.userProfile = connectedUserProfile
      });
    }
  }
  //handling login if not connected
  async handleLogin() {
    await this.keyclaokService.login({
      redirectUri: window.location.origin
    });
  }

  //handlilng logout, it remove session from keycloak
  handleLogout() {
    this.keyclaokService.logout(window.location.origin);
  }
}
