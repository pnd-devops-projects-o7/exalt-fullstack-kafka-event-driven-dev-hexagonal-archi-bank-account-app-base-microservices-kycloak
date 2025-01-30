import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { OperationManagementComponent } from "./operation-management/operation-management.component";
import { getAllOperationsResolveFn, getAllTransfersResolveFn } from "../../shared/services/backend/operation/transfer-operation.resolve.service";
import { TransfersListComponent } from "./operation-management/transfers-list/transfers-list.component";

const childRoutes: Routes = [
    {
        path: '', component: OperationManagementComponent, resolve: {
            allOperations: getAllOperationsResolveFn
        }
    },
    {
        path: "transfers", component: TransfersListComponent, resolve: {
            allTransfers: getAllTransfersResolveFn
        }
    }
]

@NgModule({
    imports: [RouterModule.forChild(childRoutes)],
    exports: [RouterModule]
})
export class OperationRoutingModule { }