import { TestBed } from '@angular/core/testing';

import { OmimService } from './omim.service';

describe('OmimService', () => {
  let service: OmimService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OmimService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
