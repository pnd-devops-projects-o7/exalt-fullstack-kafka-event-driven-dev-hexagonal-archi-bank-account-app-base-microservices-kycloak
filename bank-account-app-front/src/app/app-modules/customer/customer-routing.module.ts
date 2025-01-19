import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CustomerManagementComponent } from "./customer-management/customer-management.component";
import { getAllCustomersResolveFn } from "../../shared/services/backend/customer/resolve.customer-service";

const childRoutes: Routes = [
    {
        path: '', component: CustomerManagementComponent, resolve: {
            all: getAllCustomersResolveFn
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(childRoutes)],
    exports: [RouterModule]
})
export class CustomerRoutingModule {}