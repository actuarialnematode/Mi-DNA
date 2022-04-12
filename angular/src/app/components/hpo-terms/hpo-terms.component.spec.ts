import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HpoTermsComponent } from './hpo-terms.component';

describe('HpoTermsComponent', () => {
  let component: HpoTermsComponent;
  let fixture: ComponentFixture<HpoTermsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HpoTermsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HpoTermsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
