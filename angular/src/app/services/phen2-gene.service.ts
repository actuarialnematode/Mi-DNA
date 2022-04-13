import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { HpoTerm } from '../classes/hpo-term';
import { PhenGenes } from '../classes/phen-genes';
import { PhenGene } from '../classes/phen-gene';

@Injectable({
  providedIn: 'root'
})
export class Phen2GeneService {

  terms:Array<HpoTerm>;
  hpoIds:Array<string>;
  
  private PHEN2GENE_URL = environment.serviceUrl + environment.phen2geneEndpoint;

  constructor(private http: HttpClient) {
    this.terms = new Array<HpoTerm>(); 
    this.hpoIds = new Array<string>();
  }

  getPhenGenes()
  {
    let notes = localStorage.getItem("notes");
    if (notes != null) {
      this.terms= JSON.parse(notes);
    } else {
      this.terms= new Array<HpoTerm>();
    } for(let hpo of this.terms) {
      this.hpoIds.push(hpo.hpo_id);
    }
    return this.http.get<Array<PhenGene>> (this.PHEN2GENE_URL+"?hpos="+this.hpoIds, {});
  }
  
}
