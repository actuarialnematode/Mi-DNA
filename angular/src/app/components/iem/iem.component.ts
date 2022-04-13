import { Component, OnInit } from '@angular/core';
import { Iem } from 'src/app/classes/iem';
import { IemBaseService } from 'src/app/services/iem-base.service';
import { PhenGenes } from '../../classes/phen-genes';
import { IemSymptom } from '../../classes/iem-symptom';
import { Iems } from '../../classes/iems';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-iem',
  templateUrl: './iem.component.html',
  styleUrls: ['./iem.component.css']
})
export class IemComponent implements OnInit {

  phenGenes:PhenGenes;
  iems:Iems;
  env=environment;

  constructor(private iemService:IemBaseService) { 
    this.iems = new Iems()
    this.phenGenes = new PhenGenes();
  }

  ngOnInit(): void {
    this.env.isLoading=true;
    let overlay = document.getElementById("overlay");
    if(overlay!=null)
    {
      overlay.style.display="block";
    }
    this.phenGenes = new PhenGenes();
    let loadedGenes = localStorage.getItem("genes");
    if(loadedGenes!= null)
    {
      this.phenGenes.terms = JSON.parse(loadedGenes);
      this.iemService.getIEMs(this.phenGenes).subscribe(
        (response) =>
        {
          console.log("IEMS response: ")
          console.log(response);
          this.iems.iems = response;
          localStorage.setItem("iems",JSON.stringify(this.iems));
          this.env.isLoading=false;
          if(overlay!=null)
          { 
            overlay.style.display="none";
          }
        }
      );
    }
  }

}
