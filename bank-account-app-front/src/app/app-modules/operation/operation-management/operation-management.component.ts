import { Component, inject, OnInit } from '@angular/core';
import { OperationResponseDto } from '../../../shared/models/backend/operation/operation-response.dto';
import { OperationsListComponent } from './operations-list/operations-list.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { observableComplete } from '../../../shared/const-variables/const-variables';

@Component({
  selector: 'app-operation-management',
  standalone: true,
  imports: [OperationsListComponent, CommonModule],
  templateUrl: './operation-management.component.html',
  styleUrl: './operation-management.component.css'
})
export class OperationManagementComponent  implements OnInit{
  operations!: Array<OperationResponseDto>;
  activatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe({
      next: ({allOperations}) =>{
        this.operations = allOperations;
        return allOperations;
      },
      error: ()=>{
        return null;
      },
      complete: ()=>console.log(observableComplete)
    });
  }
}

