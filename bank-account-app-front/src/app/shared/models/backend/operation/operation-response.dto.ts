import { AccountResponseDto } from "../account/account-response.dto";

export interface OperationResponseDto {
    operationId: string;
    operationType: string;
    transactionAmount: number;
    description: string
    createdAt: Date;
    accountResponseDto: AccountResponseDto;
}