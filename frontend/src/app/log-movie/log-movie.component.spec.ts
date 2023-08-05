import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogMovieComponent } from './log-movie.component';

describe('LogMovieComponent', () => {
  let component: LogMovieComponent;
  let fixture: ComponentFixture<LogMovieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LogMovieComponent]
    });
    fixture = TestBed.createComponent(LogMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
