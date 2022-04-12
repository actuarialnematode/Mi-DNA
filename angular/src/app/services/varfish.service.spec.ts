import { TestBed } from '@angular/core/testing';

import { VarfishService } from './varfish.service';

describe('VarfishService', () => {
  let service: VarfishService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VarfishService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
