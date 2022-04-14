import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  private readonly REGISTER_URL = environment.serviceUrl + environment.registerEndpoint;

  register(user:User)
  {
    return this.http.post<User>
    (
      this.REGISTER_URL,user, {
        headers: {
          'Content-Type': 'application/json; charset=UTF-8'
        }
      });
  }

}
