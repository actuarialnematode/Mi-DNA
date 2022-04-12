import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhenGeneComponent } from './phen-gene.component';

describe('PhenGeneComponent', () => {
  let component: PhenGeneComponent;
  let fixture: ComponentFixture<PhenGeneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhenGeneComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhenGeneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
