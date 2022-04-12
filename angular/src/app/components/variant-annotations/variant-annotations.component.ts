import { Component, OnInit } from '@angular/core';
import { GenomeLocation } from '../../classes/genome-location';
import { VarfishService } from '../../services/varfish.service';

@Component({
  selector: 'app-variant-annotations',
  templateUrl: './variant-annotations.component.html',
  styleUrls: ['./variant-annotations.component.css']
})
export class VariantAnnotationsComponent implements OnInit {

  headers:Array<string>;
  rows:Array<GenomeLocation>;
  cases:Array<string>;
  selectedDevice:string;

  constructor(private varfish:VarfishService) { 
    this.selectedDevice="";
    this.cases= new Array<string>();
    //this.cases[0]="Test";
    varfish.getCases().subscribe(
      (response: string[]) => {
        this.cases = response;    
      }
    );
    this.headers= new Array<string>();
    

    this.rows=new Array<GenomeLocation>();
    let r = new Array<string>();
    for(let i=0;i<36;i++)
    {
      r.push(i.toString());
    }
    
    let g = new GenomeLocation(r);
    // this.rows.push(g);
    // this.rows.push(g);

    this.headers = g.describe();
  }

  ngOnInit(): void {
  }

  onChange(value:string){
    this.selectedDevice=value;
    console.log("Selected value is : "+ this.selectedDevice);
    this.varfish.getGenomeLocationsByCase(this.selectedDevice).subscribe(
      (response: any) => {
        
      
        for(let location of response)
        {
          console.log(typeof (location));
          console.log(location["caseNumber"]);
          let r = new Array<string>();
          for(let i of this.headers)
          {
            r.push(location[i]);
          }
          let g = new GenomeLocation(r);
          this.rows.push(g)
        }
      }
    );
  }

}
