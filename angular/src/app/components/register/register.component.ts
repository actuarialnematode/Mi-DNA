import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName: string;
  lastName: string;
  email: string;
  confirmEmail: string;
  password: string;
  confirmPassword: string;

  constructor(private regService:RegisterService, private router:Router) {
    this.firstName="";
    this.lastName="";
    this.email="";
    this.confirmEmail="";
    this.password="";
    this.confirmPassword="";
  }

  ngOnInit(): void {
  }

  register(){
      this.regService.register(new User(this.email,this.password,this.firstName,this.lastName)).subscribe(
        (response) => {
            if(response!=null)
            {
              this.router.navigate(['/import'], {replaceUrl: true});
              localStorage.setItem('token', response.token);
            } else
            {
              alert("Invalid user");
            }

        }

      );
  }

}
