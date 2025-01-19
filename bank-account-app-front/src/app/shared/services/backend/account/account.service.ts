import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AccountRequestDto } from '../../../models/backend/account/account-request.dto';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { AccountResponseDto } from '../../../models/backend/account/account-response.dto';
import { appHeaders } from '../../../headers/app.header';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient: HttpClient) { }
  accountBasePath: string = `${environment.gatewayApiService}/bank-account`;

  createAccount(accountRequestDto: AccountRequestDto): Observable<AccountResponseDto> {
    const accountResponseDto: Observable<AccountResponseDto> = this.httpClient.
      post<AccountResponseDto>(`${this.accountBasePath}/accounts`, accountRequestDto, { headers: appHeaders });
    return accountResponseDto;
  }

  loadAllAccounts(): Observable<Array<AccountResponseDto>> {
    const accountsResponseDto: Observable<Array<AccountResponseDto>> = this.httpClient
      .get<Array<AccountResponseDto>>(`${this.accountBasePath}/accounts`);
    return accountsResponseDto;
  }

  switchAccountState(accountId: string): Observable<AccountResponseDto> {
    const accountResponseDto: Observable<AccountResponseDto> = this.httpClient
      .post<AccountResponseDto>(`${this.accountBasePath}/accounts/switch-state/${accountId}`, { headers: appHeaders });
    return accountResponseDto;
  }
  updateAccountOverdraftOrInterestRate(accountId: string, overdraftOrIrate: number): Observable<AccountResponseDto> {
    const overdraftOrIrateParam = new HttpParams().append('overdraft_irate', overdraftOrIrate)
    const accountResponseDto: Observable<AccountResponseDto> = this.httpClient
      .post<AccountResponseDto>(`${this.accountBasePath}/accounts/update-overdraft-irate/${accountId}?${overdraftOrIrateParam}`,
        { headers: appHeaders },

      );
    return accountResponseDto;
  }
}
