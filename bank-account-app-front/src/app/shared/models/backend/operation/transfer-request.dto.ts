export interface TransferRequestDto {
    originAccount: string;
    destinationAccount: string;
    transferAmount: number;
    description: string;
}