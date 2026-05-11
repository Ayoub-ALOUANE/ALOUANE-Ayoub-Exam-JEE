import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientContracts } from './client-contracts';

describe('ClientContracts', () => {
  let component: ClientContracts;
  let fixture: ComponentFixture<ClientContracts>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientContracts],
    }).compileComponents();

    fixture = TestBed.createComponent(ClientContracts);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
