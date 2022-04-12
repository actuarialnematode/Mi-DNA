import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VariantAnnotationsComponent } from './variant-annotations.component';

describe('VariantAnnotationsComponent', () => {
  let component: VariantAnnotationsComponent;
  let fixture: ComponentFixture<VariantAnnotationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VariantAnnotationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VariantAnnotationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
