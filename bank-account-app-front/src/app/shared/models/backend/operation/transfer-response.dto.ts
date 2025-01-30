import { AccountResponseDto } from "../account/account-response.dto";

export interface TransferResponseDto {
    transferId: string;
    transferAmount: number;
    description: string;
    createdAt: Date;
    originAccount: string;
    destinationAccount: string;
    origin: AccountResponseDto;
    destination: AccountResponseDto;
}