import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  selectedTab:string;
  env= environment;
  status1:boolean;
  status2:boolean;
  status3:boolean;
  status4:boolean;
  status5:boolean;

  constructor() { 
    this.selectedTab="";
    this.env.isLoading=false;
    this.status1 = false;
    this.status2 = false;
    this.status3 = false;
    this.status4 = false;
    this.status5 = false;
  }

  ngOnInit(): void {
  }

  showVariants(){
    this.selectedTab="variants";
    this.env.isLoading=true;
    this.status1=!this.status1;
    this.status2 = false;
    this.status3 = false;
    this.status4 = false;
    this.status5 = false;
  }

  showHPO(){
    this.selectedTab="hpo";
    this.env.isLoading=true;
    this.status2=!this.status2;
    this.status1 = false;
    this.status3 = false;
    this.status4 = false;
    this.status5 = false;
  }

  showPhen(){
    this.selectedTab="phen";
    this.env.isLoading=true;
    this.status3=!this.status3;
    this.status2 = false;
    this.status1 = false;
    this.status4 = false;
    this.status5 = false;
  }

  showIem(){
    this.selectedTab="iem";
    this.env.isLoading=true;
    this.status4=!this.status4;
    this.status2 = false;
    this.status3 = false;
    this.status1 = false;
    this.status5 = false;
  }

  showOMIM() {
    this.selectedTab="omim";
    this.env.isLoading=true;
    this.status5=!this.status5;
    this.status2 = false;
    this.status3 = false;
    this.status4 = false;
    this.status1 = false;
  }

}
