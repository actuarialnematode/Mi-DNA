import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;
 
  constructor(private authService:AuthService, private router:Router) {
    this.email="";
    this.password="";
   }

  ngOnInit(): void {
  }


  authenticate(){
      console.log("Email: "+this.email);
      console.log("Password: "+this.password);
      this.authService.authenticate(new User(this.email,this.password,'','')).subscribe(
        (response) => {
            if(response!=null && response.email != null && response.token != null)
            {
              this.router.navigate(['/dashboard'], {replaceUrl: true});
              localStorage.setItem('user', response.email);
              localStorage.setItem('token', response.token);
            }else
            {
              alert("Invalid user");
            }

        }

      );
  }


}
