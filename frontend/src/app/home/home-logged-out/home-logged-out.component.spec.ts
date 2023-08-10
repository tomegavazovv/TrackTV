import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeLoggedOutComponent } from './home-logged-out.component';

describe('HomeLoggedOutComponent', () => {
  let component: HomeLoggedOutComponent;
  let fixture: ComponentFixture<HomeLoggedOutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeLoggedOutComponent]
    });
    fixture = TestBed.createComponent(HomeLoggedOutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
