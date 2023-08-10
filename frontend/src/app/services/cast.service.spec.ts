import { TestBed } from '@angular/core/testing';

import { CastService } from './cast.service';

describe('CastServiceService', () => {
  let service: CastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
