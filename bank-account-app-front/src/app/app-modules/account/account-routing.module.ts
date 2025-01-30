import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AccountManagementComponent } from "./account-management/account-management.component";
import { getAllAccountsResolveFn } from "../../shared/services/backend/account/resolve.account-service";

const childRoutes: Routes = [
    {
        path: '', component: AccountManagementComponent, resolve:{
            allAccounts: getAllAccountsResolveFn
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(childRoutes)],
    exports: [RouterModule]
})
export class AccountRoutingModule {}