import { Component, inject, Input, OnInit } from '@angular/core';
import { TransferResponseDto } from '../../../../shared/models/backend/operation/transfer-response.dto';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { observableComplete } from '../../../../shared/const-variables/const-variables';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-transfers-list',
  standalone: true,
  imports: [CommonModule, TableModule, InputTextModule],
  templateUrl: './transfers-list.component.html',
  styleUrl: './transfers-list.component.css'
})
export class TransfersListComponent implements OnInit {
  transfers!: Array<TransferResponseDto>;
  activatedRoute = inject(ActivatedRoute);
  style1:string ="font-size: normal;";
  style2: string = "font-weight: bold;"

  ngOnInit(): void {
    this.activatedRoute.data.subscribe({
      next: ({ allTransfers }) => {
        this.transfers = allTransfers;
        return allTransfers;
      },
      error: () => {
        return null;
      },
      complete: () => console.log(observableComplete)
    });
  }


  // to back to table page where we were before going to anothe page
  first: number = Number(localStorage.getItem('transferPage'))
  onTablePageChange($event: any) {
    this.first = $event.first;
    localStorage.setItem('transferPage', this.first.toString());
  }
}

