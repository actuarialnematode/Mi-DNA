import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Doc2HPOService } from '../../services/doc2-hpo.service';
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})



export class ImportComponent implements OnInit {
  SERVER_URL = "http://localhost:8081/upload";
  uploadForm: FormGroup;
  notes: string;
  env = environment;
  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient, private hpoService: Doc2HPOService,private authService:AuthService,private router:Router) {
    authService.checkSession().subscribe(
      (response)=>{
        if(response.token==null || response.email==null)
        {
          this.router.navigate(['/login'], {replaceUrl: true});
        }
      }
    );
    this.notes = "";
    this.uploadForm = this.formBuilder.group({
      file: ['']
    });
  }

  ngOnInit(): void {

  }

  onFileSelect(event: any) {
    // number of files selected
    if (event.target.files.length > 0) {
      // 1st file selected
      const file = event.target.files[0];
      // @ts-ignore: Object is possibly 'null'.
      this.uploadForm.get('file').setValue(file);
    }
  }

  onSubmit() {
    const formData = new FormData();
    this.env.isLoading = true;
    let overlay = document.getElementById("overlay");
    if (overlay != null) {
      overlay.style.display = "block";
    }
    // @ts-ignore: Object is possibly 'null'.
    // key = name, value = value of name from form group
    formData.append('file', this.uploadForm.get('file').value);


    console.log("In submit")
    console.log("Current text : " + this.notes);
    if (this.notes.length != 0) {
      console.log("Sending notes");
      this.hpoService.getHpoTerms(this.notes).subscribe(
        (response) => {
          localStorage.setItem("notes", JSON.stringify(response));

        }
      );
    }
    console.log("Uploading file");
    this.httpClient.post<any>(this.SERVER_URL, formData).subscribe(
      (res) => {
        console.log(res);
        this.env.isLoading = false;
        if (overlay != null) {
          overlay.style.display = "none";
        }
      },
      (err) => console.log(err)
    );



  }

}
