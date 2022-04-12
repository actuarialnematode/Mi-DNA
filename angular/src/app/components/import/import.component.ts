import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Doc2HPOService } from '../../services/doc2-hpo.service';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.css']
})


export class ImportComponent implements OnInit {
  SERVER_URL = "http://localhost:8081/upload";
  uploadForm: FormGroup;
  notes:string;
  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient,private hpoService:Doc2HPOService) { 
    this.notes="";
    this.uploadForm = this.formBuilder.group({
      file: ['']
      });
  }

  ngOnInit(): void {
    
  }
  
  onFileSelect(event:any) {
    if (event.target.files.length > 0) {
    const file = event.target.files[0];
    // @ts-ignore: Object is possibly 'null'.
    this.uploadForm.get('file').setValue(file);
    }
    } 

  onSubmit() {
    const formData = new FormData();
    
    // @ts-ignore: Object is possibly 'null'.
    formData.append('file', this.uploadForm.get('file').value);
    

    console.log("In submit")
    console.log("Current text : "+this.notes);
    if(this.notes.length!=0)
    {
      console.log("Sending notes");
      this.hpoService.getHpoTerms(this.notes).subscribe(
        (response) => {
          localStorage.setItem("notes",JSON.stringify(response));
        }
      );
    } 
    console.log("Uploading file");
    this.httpClient.post<any>(this.SERVER_URL, formData).subscribe(
    (res) => console.log(res),
    (err) => console.log(err)
    );
    
   
  }

}
