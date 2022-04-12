import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private readonly LOGIN_URL = environment.serviceUrl + environment.loginEndpoint;

  authenticate(user:User)
  {
    return this.http.post<User>
    (
      this.LOGIN_URL,user, {
        headers: {
          'Content-Type': 'application/json; charset=UTF-8'
        }
      });
  }


  // getCategories()
  // {
  //   return this.http.get<UserVerificationRequirement>
  //   (
  //     this.LOGIN_URL, {headers: {
  //       'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
  //     }
  //     });
  // }
}
