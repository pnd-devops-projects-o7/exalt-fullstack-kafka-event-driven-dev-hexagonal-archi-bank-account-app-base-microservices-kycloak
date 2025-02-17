import { Component } from '@angular/core';
import { SplitterModule } from 'primeng/splitter';


@Component({
  selector: 'app-accueil',
  standalone: true,
  imports: [ 
   SplitterModule
  ],
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent {
  imagePath: string;
  imgStyle: string;
  gitRepository: string;
  constructor(){
    this.imagePath = "./assets/archi/exalt-bank-account-app-archi-fullstack.webm";
    this.imgStyle = "width: 1550px; height: 750px;"
    this.gitRepository = "https://github.com/pnd-devops-projects-o7/exalt-fullstack-kafka-event-driven-dev-hexagonal-archi-bank-account-app-base-microservices-kycloak.git";
  }
}
