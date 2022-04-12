import { TestBed } from '@angular/core/testing';

import { Phen2GeneService } from './phen2-gene.service';

describe('Phen2GeneService', () => {
  let service: Phen2GeneService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Phen2GeneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
