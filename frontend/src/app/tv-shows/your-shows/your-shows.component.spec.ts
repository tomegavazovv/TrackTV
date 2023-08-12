import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YourShowsComponent } from './your-shows.component';

describe('YourShowsComponent', () => {
  let component: YourShowsComponent;
  let fixture: ComponentFixture<YourShowsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [YourShowsComponent]
    });
    fixture = TestBed.createComponent(YourShowsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
