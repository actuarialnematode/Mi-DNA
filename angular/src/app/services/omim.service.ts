import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Omim } from '../classes/omim';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OmimService {

  /* need to change environment.caseEndpint to OMIM endpoint */
  private OMIM_URL = environment.serviceUrl + environment.omimEndpoint;

  constructor(private http: HttpClient) { }

  /* need to change caseNumber variable to OMIM input variable */
  getOmimTerms(omimNo:Array<string>)
  {
    let formData: FormData = new FormData()
    formData.append("iemOMIMs", "248000,220110");
    return this.http.post<Array<Omim>>
    (
      this.OMIM_URL,formData);
  }

}
