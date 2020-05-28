import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectingComponent } from './collecting.component';

describe('CollectingComponent', () => {
  let component: CollectingComponent;
  let fixture: ComponentFixture<CollectingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollectingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
