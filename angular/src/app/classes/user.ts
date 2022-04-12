export class User {
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
