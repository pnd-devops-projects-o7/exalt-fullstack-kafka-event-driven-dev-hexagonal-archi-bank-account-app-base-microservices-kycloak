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

  onColoring(value: string) : string {
    switch(value) {
      case "DEPOSIT":
        return "color: forestgreen";
      case "WITHDRAW":
        return "color: #f00";
      case "ACTIVE":
        return "color: forestgreen";
      case "SUSPENDED":
        return "color: #f00";
      default:
        return "color: forestgreen;";
    }
  }
}
