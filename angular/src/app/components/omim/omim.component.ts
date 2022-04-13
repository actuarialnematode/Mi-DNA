import { Component, OnInit } from '@angular/core';
import { Omim } from 'src/app/classes/omim';
import { OmimService } from 'src/app/services/omim.service';
import { Iem } from 'src/app/classes/iem';
import { Iems } from 'src/app/classes/iems';
import { Omims } from '../../classes/omims';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-omim',
  templateUrl: './omim.component.html',
  styleUrls: ['./omim.component.css']
})
export class OmimComponent implements OnInit {

  iems: Iems;
  omims:Array<string>;
  omimData:Omims;
  env = environment;

  constructor(private omimService:OmimService) {
    let iem = localStorage.getItem("iems");
    this.omims = new Array<string>();
    this.omimData = new Omims();
    let overlay = document.getElementById("overlay");
    if(overlay!=null)
    {
      overlay.style.display="block";
    }
    if (iem != null) {
      this.iems = JSON.parse(iem);
      for (let iem of this.iems.iems){
        this.omims.push(iem.iem_omim);
      }
      console.log("Omims:",this.omims);
      omimService.getOmimTerms(this.omims).subscribe(
        (response) => {
          console.log(response);
          this.omimData.omims = response;
          this.env.isLoading==false;
          if(overlay!=null)
          { 
            overlay.style.display="none";
          }
        }
      );
    } else {
      this.iems = new Iems();
    }
  }

  ngOnInit(): void {
  }

}
