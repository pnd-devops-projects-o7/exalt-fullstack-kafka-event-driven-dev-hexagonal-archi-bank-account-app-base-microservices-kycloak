import { inject, Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { TransferRequestDto } from '../../../models/backend/operation/transfer-request.dto';
import { Observable } from 'rxjs';
import { appHeaders } from '../../../headers/app.header';
import { TransferResponseDto } from '../../../models/backend/operation/transfer-response.dto';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  operationBasePath: string = `${environment.gatewayApiService}/operation`;
    httpClient = inject(HttpClient);
  createTransfer(transferRequestDto: TransferRequestDto): Observable<TransferResponseDto> {
    const transferResponseDto: Observable<TransferResponseDto> = this.httpClient
      .post<TransferResponseDto>(`${this.operationBasePath}/transfers`, transferRequestDto, { headers: appHeaders });
    return transferResponseDto;
  }

  loadAllTransfers(): Observable<Array<TransferResponseDto>> {
    const transfers: Observable<Array<TransferResponseDto>> = this.httpClient
    .get<Array<TransferResponseDto>>(`${this.operationBasePath}/transfers`);

    return transfers;
  }
}
