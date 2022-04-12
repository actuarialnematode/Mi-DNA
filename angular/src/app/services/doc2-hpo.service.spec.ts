import { TestBed } from '@angular/core/testing';

import { Doc2HPOService } from './doc2-hpo.service';

describe('Doc2HPOService', () => {
  let service: Doc2HPOService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Doc2HPOService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
