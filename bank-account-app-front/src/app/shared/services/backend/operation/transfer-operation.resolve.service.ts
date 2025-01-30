import { ResolveFn } from "@angular/router";
import { OperationResponseDto } from "../../../models/backend/operation/operation-response.dto";
import { inject } from "@angular/core";
import { OperationService } from "./operation.service";
import { TransferResponseDto } from "../../../models/backend/operation/transfer-response.dto";
import { TransferService } from "./transfer.service";

export const getAllOperationsResolveFn: ResolveFn<Array<OperationResponseDto>> = ()=>{
    return inject(OperationService).loadAllOperations();
}

export const getAllTransfersResolveFn: ResolveFn<Array<TransferResponseDto>> = ()=>{
    return inject(TransferService).loadAllTransfers();
}