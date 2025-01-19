import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { OperationRequestDto } from '../../../models/backend/operation/operation-request.dto';
import { Observable } from 'rxjs';
import { OperationResponseDto } from '../../../models/backend/operation/operation-response.dto';
import { HttpClient, HttpParams } from '@angular/common/http';
import { appHeaders } from '../../../headers/app.header';

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  operationBasePath: string = `${environment.gatewayApiService}/operation`;
  httpClient = inject(HttpClient);

  createOperation(operationRequestDto: OperationRequestDto, transactionAmount: number): Observable<OperationResponseDto> {
    const transactionAmountParam = new HttpParams().append("transaction-amount", transactionAmount)
    const operationResponseDto: Observable<OperationResponseDto> = this.httpClient
      .post<OperationResponseDto>(`${this.operationBasePath}/operations?${transactionAmountParam}`, operationRequestDto, { headers: appHeaders })

    return operationResponseDto;
  }

  loadAllOperations(): Observable<Array<OperationResponseDto>> {
    const operationsResponseDto: Observable<Array<OperationResponseDto>> = this.httpClient
      .get<Array<OperationResponseDto>>(`${this.operationBasePath}/operations`);

    return operationsResponseDto;
  }
}
