import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { InsuranceService } from '../services/insurance';
import { Contract, Payment } from '../models/model';
import { AuthService } from '../services/auth';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-client-contracts',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client-contracts.html',
  styleUrl: './client-contracts.css'
})
export class ClientContractsComponent implements OnInit {
  clientId!: number;
  contracts: Contract[] = [];
  selectedContract?: Contract;
  payments: Payment[] = [];
  newPayment: Payment = { montant: 0, type: 'MENSUALITE' };
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private insuranceService: InsuranceService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.clientId = this.route.snapshot.params['clientId'];
    this.loadContracts();
  }

  loadContracts() {
    this.insuranceService.getClientContracts(this.clientId).subscribe({
      next: (data) => this.contracts = data,
      error: (err) => this.errorMessage = "Erreur chargement contrats"
    });
  }

  onValidate(contractId: number) {
    this.insuranceService.validateContract(contractId).subscribe({
      next: () => this.loadContracts(),
      error: (err) => this.errorMessage = "Erreur validation"
    });
  }

  showPayments(contract: Contract) {
    this.selectedContract = contract;
    this.insuranceService.getContractPayments(contract.id!).subscribe({
      next: (data) => this.payments = data,
      error: (err) => this.errorMessage = "Erreur chargement paiements"
    });
  }

  onAddPayment() {
    if (this.selectedContract) {
      this.insuranceService.addPayment(this.selectedContract.id!, this.newPayment).subscribe({
        next: (p) => {
          this.payments.push(p);
          this.newPayment = { montant: 0, type: 'MENSUALITE' };
        },
        error: (err) => this.errorMessage = "Erreur ajout paiement"
      });
    }
  }
}
