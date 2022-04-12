import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ImportComponent } from './components/import/import.component';
import { VariantAnnotationsComponent } from './components/variant-annotations/variant-annotations.component';
import { HpoTermsComponent } from './components/hpo-terms/hpo-terms.component';
import { PhenGeneComponent } from './components/phen-gene/phen-gene.component';
import { IemComponent } from './components/iem/iem.component';
import { OmimComponent } from './components/omim/omim.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ImportComponent,
    VariantAnnotationsComponent,
    HpoTermsComponent,
    PhenGeneComponent,
    IemComponent,
    OmimComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
