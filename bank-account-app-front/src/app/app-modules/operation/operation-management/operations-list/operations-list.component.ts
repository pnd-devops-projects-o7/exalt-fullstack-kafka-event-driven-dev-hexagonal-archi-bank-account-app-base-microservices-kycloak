import { Component, Input } from '@angular/core';
import { OperationResponseDto } from '../../../../shared/models/backend/operation/operation-response.dto';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-operations-list',
  standalone: true,
  imports: [CommonModule, TableModule, InputTextModule],
  templateUrl: './operations-list.component.html',
  styleUrl: './operations-list.component.css'
})
export class OperationsListComponent {
  @Input() operations!: Array<OperationResponseDto>;
  style: string ="font-weight: bold";

  // to back to table page where we were before going to anothe page
  first: number = Number(localStorage.getItem('operationPage'))
  onTablePageChange($event: any) {
    this.first = $event.first;
    localStorage.setItem('operationPage', this.first.toString());
  }

  onGetOperationypeStyle(opType: string) : string {
    if(opType==="DEPOSIT") {
      return "font-weight: bold; color: forestgreen";
    }
    else {
      return "font-weight: bold; color: #f00";
    }
  }
}
