import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryHomePageComponent } from './inventory-home-page.component';

describe('InventoryHomePageComponent', () => {
  let component: InventoryHomePageComponent;
  let fixture: ComponentFixture<InventoryHomePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InventoryHomePageComponent]
    });
    fixture = TestBed.createComponent(InventoryHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
