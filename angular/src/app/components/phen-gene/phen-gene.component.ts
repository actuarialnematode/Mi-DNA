import { Component, OnInit } from '@angular/core';
import { PhenGene } from 'src/app/classes/phen-gene';
import { PhenGenes } from 'src/app/classes/phen-genes';
import { Phen2GeneService } from 'src/app/services/phen2-gene.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-phen-gene',
  templateUrl: './phen-gene.component.html',
  styleUrls: ['./phen-gene.component.css']
})
export class PhenGeneComponent implements OnInit {

  phenGenes:PhenGenes;
  env=environment;

  constructor(private phenService:Phen2GeneService) {
    this.phenGenes = new PhenGenes();
    let overlay = document.getElementById("overlay");
    if(overlay!=null)
    {
      // overlay.style.display="block";
    }
    phenService.getPhenGenes().subscribe(
      (response) =>
      {
        this.phenGenes.terms= response;
        //console.log(this.phenGenes.terms);
        
        localStorage.setItem("genes",JSON.stringify(this.phenGenes.terms));
        this.env.isLoading==false;
        if(overlay!=null)
        { 
          // overlay.style.display="none";
        }
      }
    );
  }

  ngOnInit(): void {
  }

}
