import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayblockComponent } from './displayblock.component';

describe('DisplayblockComponent', () => {
  let component: DisplayblockComponent;
  let fixture: ComponentFixture<DisplayblockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayblockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayblockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
