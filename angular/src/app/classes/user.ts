import { Token } from "@angular/compiler/src/ml_parser/tokens"

export class User {
    
    token:string=""
    email:string=""
    firstname:string=""
    lastname:string=""
    password:String=""

    constructor(email:string,password:string,firstname:string,lastname:string){
        this.email=email;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
    }
}
