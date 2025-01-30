import { ResolveFn } from "@angular/router";
import { AccountResponseDto } from "../../../models/backend/account/account-response.dto";
import { AccountService } from "./account.service";
import { inject } from "@angular/core";

export const getAllAccountsResolveFn : ResolveFn<Array<AccountResponseDto>> = ()=>{
    return inject(AccountService).loadAllAccounts();
}