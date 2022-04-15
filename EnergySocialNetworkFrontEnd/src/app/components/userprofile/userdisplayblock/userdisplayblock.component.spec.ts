import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdisplayblockComponent } from './userdisplayblock.component';

describe('UserdisplayblockComponent', () => {
  let component: UserdisplayblockComponent;
  let fixture: ComponentFixture<UserdisplayblockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserdisplayblockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserdisplayblockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
