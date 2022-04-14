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
  readableHeaders:Array<string>;
  rows:Array<GenomeLocation>;
  cases:Array<string>;
  selectedDevice:string;

  constructor(private varfish:VarfishService) { 
    this.selectedDevice="";
    this.cases= new Array<string>();
    //this.cases[0]="Test";

    // returns a list of varfish case names
    varfish.getCases().subscribe(
      (response: string[]) => {
        this.cases = response;    
      }
    );
    this.headers= new Array<string>();
    this.readableHeaders= new Array<string>();

    this.rows=new Array<GenomeLocation>();
    // defining the table header names in list
    let r= new Array<string>();
    // for(let i=0;i<36;i++)
    // {
    //   r.push(i.toString());
    // }

    r.push("Coordinates position");
    r.push("Coordinates ref");
    r.push("Coordinates alt");
    r.push("Clinvar sign. & rating");
    r.push("Clinvar phenotype");
    r.push("ExAC frequency");
    r.push("ExAC #hom.");
    r.push("gnomAD exomes frequency");
    r.push("gnomAD exomes #hom.");
    r.push("gnomAD genomes frequency");
    r.push("gnomAD #hom.");
    r.push("1000 genomes frequency");
    r.push("1000 genomes #hom.");
    r.push("in-house DB #carriers");
    r.push("in-house DB #hom.");
    r.push("mtDB frequency");
    r.push("mtDB #carriers");
    r.push("HelixMTdb frequency");
    r.push("HelixMTdb #hom.");
    r.push("mtDB frequency");
    r.push("mtDB #carriers");
    r.push("ExAC pLI");
    r.push("ExAC Z mis");
    r.push("ExAC Z syn");
    r.push("gnomAD LOEUF");
    r.push("gnomAD pLI");
    r.push("gnomAD Z mis");
    r.push("gnomAD Z syn");
    r.push("Disease gene");
    r.push("Effect");
    r.push("Effect text");
    r.push("Effect protein");
    r.push("Effect cDNA");
    r.push("Dist. to splice site");
    r.push("Genotype");
    
    let g = new GenomeLocation(r);
    // this.rows.push(g);
    // this.rows.push(g);
    this.readableHeaders = r;
    this.headers = g.describe();
  }

  ngOnInit(): void {
  }

  onChange(value:string){
    this.selectedDevice=value;
    console.log("Selected value is : "+ this.selectedDevice);
    // response comes back as an array of genome locations
    this.varfish.getGenomeLocationsByCase(this.selectedDevice).subscribe(
      (response: any) => {
        
        // looping through each variant in the array of variants from the server response
        for(let location of response)
        {
          console.log(typeof (location));
          console.log(location["caseNumber"]);
          let r = new Array<string>();

          // looping through each field in a variant where i=0,1,2,...35 in header array
          for(let i of this.headers)
          {
            r.push(location[i]);
          }
          // store all the field data into a genomeLoc object
          let g = new GenomeLocation(r);
          // add this newly create genome location into our genomeLocation array
          this.rows.push(g)
        }
      }
    );
  }

}
