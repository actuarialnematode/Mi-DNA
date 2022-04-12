import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  selectedTab:string;

  constructor() { 
    this.selectedTab="";
  }

  ngOnInit(): void {
  }

  showVariants(){
    this.selectedTab="variants";
  }

  showHPO(){
    this.selectedTab="hpo";
  }

  showPhen(){
    this.selectedTab="phen";
  }

  showIem(){
    this.selectedTab="iem";
  }

  showOMIM() {
    this.selectedTab="omim";
  }

}
