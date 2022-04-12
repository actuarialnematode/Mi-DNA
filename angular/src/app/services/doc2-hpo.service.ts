import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { HpoTerm } from '../classes/hpo-term';
import { HpoTerms } from '../classes/hpo-terms';

@Injectable({
  providedIn: 'root'
})
export class Doc2HPOService {

  /* need to change environment.caseEndpint to Doc2HPO endpoint */
  private HPO_URL = environment.serviceUrl + environment.doc2hpoEndpoint;

  constructor(private http: HttpClient) { }

  /* need to change caseNumber variable to Doc2HPO input variable */
  getHpoTerms(text:string)
  {
    console.log("Sending request to "+this.HPO_URL);
    return this.http.get<HpoTerms>
    (
      this.HPO_URL+"?text="+text, {});
  }

}
