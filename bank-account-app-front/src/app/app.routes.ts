import { Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { AppEntryComponent } from './app-entry/app-entry.component';
import { AppAuthGuard } from './shared/services/authentication/app-auth.guard';

export const routes: Routes = [
    {
        path: 'accueil', component: AccueilComponent
    },
    {
        path: 'app-menu', component: AppEntryComponent, canActivate: [AppAuthGuard], data: { roles: ['ADMIN', 'OWNER', 'USER'] },
        children: [
            {
                path: 'customers', loadChildren: () => import('./app-modules/customer/customer.module')
                .then(module => module.CustomerModule)
            },
            {
                path: 'accounts', loadChildren: () => import('./app-modules/account/account.module')
                .then(module => module.AccountModule)
            },
            {
                path: 'operations', loadChildren: () => import('./app-modules/operation/operation.module')
                .then(module => module.OperationModule)
            }
        ]
    }
];
