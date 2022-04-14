import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Iem } from '../classes/iem';
import { Observable } from 'rxjs';
import { PhenGene } from '../classes/phen-gene';
import { PhenGenes } from '../classes/phen-genes';

@Injectable({
  providedIn: 'root'
})
export class IemBaseService {

  /* need to change environment.caseEndpint to IEM endpoint */
  private IEM_URL = environment.serviceUrl + environment.iemEndpoint;

  constructor(private http: HttpClient) { }

  /* need to change caseNumber variable to IEM input variable */
  getIEMs(genesArr:PhenGenes)
  {
    let genes = new Array<String>();
    let formData: FormData = new FormData()
    
    // genes.push("DDC","WDR45");
    for(let gene of genesArr.terms)
    {
        genes.push(gene.phen_gene);
    }
    console.log("Passing in "+JSON.stringify(genes));
    formData.append("genes", JSON.stringify(genes));
    return this.http.post<Array<Iem>>
    (
      this.IEM_URL,formData);
  }
}
