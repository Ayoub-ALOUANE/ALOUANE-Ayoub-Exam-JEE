import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeContract } from './subscribe-contract';

describe('SubscribeContract', () => {
  let component: SubscribeContract;
  let fixture: ComponentFixture<SubscribeContract>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubscribeContract],
    }).compileComponents();

    fixture = TestBed.createComponent(SubscribeContract);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
