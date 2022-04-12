import { TestBed } from '@angular/core/testing';

import { IemBaseService } from './iem-base.service';

describe('IemBaseService', () => {
  let service: IemBaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IemBaseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
