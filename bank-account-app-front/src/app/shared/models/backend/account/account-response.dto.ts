import { CustomerResponseDto } from "../customer/customer-response.dto";

export interface AccountResponseDto {
    accountId: string;
    accountType: string;
    accountState: string;
    balance: number;
    overdraft: number;
    interestRate: number;
    createdAt: Date;
    customerResponseDto: CustomerResponseDto;
}