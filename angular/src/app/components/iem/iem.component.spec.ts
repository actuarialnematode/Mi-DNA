import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IemComponent } from './iem.component';

describe('IemComponent', () => {
  let component: IemComponent;
  let fixture: ComponentFixture<IemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
