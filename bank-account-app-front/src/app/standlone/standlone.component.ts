import { Component } from '@angular/core';

@Component({
  selector: 'app-standlone',
  standalone: true,
  imports: [],
  templateUrl: './standlone.component.html',
  styleUrl: './standlone.component.css'
})
export class StandloneComponent {
  imagePath: string;
  imgStyle: string;
  constructor(){
    this.imagePath = "./assets/archi/archi-summary.gif";
  this.imgStyle = "width: 1550px; height: 750px;"
  } 

}
