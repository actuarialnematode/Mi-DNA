import { Component, OnInit } from '@angular/core';
import { HpoTerm } from '../../classes/hpo-term';
import { Doc2HPOService } from '../../services/doc2-hpo.service';

@Component({
  selector: 'app-hpo-terms',
  templateUrl: './hpo-terms.component.html',
  styleUrls: ['./hpo-terms.component.css']
})
export class HpoTermsComponent implements OnInit {

  terms:Array<HpoTerm>;

  constructor() { 
    let notes = localStorage.getItem("notes");
    if(notes != null)
    {
      this.terms= JSON.parse(notes);
    }else
    {
      this.terms= new Array<HpoTerm>();
    }
    
  }

  ngOnInit(): void {
  }

}
