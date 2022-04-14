import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { GenomeLocation } from '../classes/genome-location';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VarfishService {

  private readonly CASES_URL = environment.serviceUrl + environment.allCasesEndpoint;
  private CASE_URL = environment.serviceUrl + environment.caseEndpoint;

  constructor(private http: HttpClient) { }

  // returns a list of varfish case names
  getCases(): any
  {
    console.log(this.CASES_URL);
    return this.http.get<Array<string>>
    (
      this.CASES_URL, {});
  }
  
  // get request specifies return object of array of genome locations
  getGenomeLocationsByCase(caseNumber:string): any
  {
    return this.http.get<Array<GenomeLocation>>
    (
      this.CASE_URL+caseNumber, {});
  }

}
