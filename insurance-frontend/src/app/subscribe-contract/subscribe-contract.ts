import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { InsuranceService } from '../services/insurance';

@Component({
  selector: 'app-subscribe-contract',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './subscribe-contract.html',
  styleUrl: './subscribe-contract.css'
})
export class SubscribeContractComponent implements OnInit {
  clientId!: number;
  contractType: string = 'AUT';
  
  // Basic Contract Info
  montantCotisation: number = 0;
  duree: number = 12;
  tauxCouverture: number = 0.8;

  // Automobile
  matricule: string = '';
  marque: string = '';
  modele: string = '';

  // Habitation
  typeLogement: string = 'APPARTEMENT';
  adresse: string = '';
  superficie: number = 0;

  // Sante
  niveauCouverture: string = 'BASIQUE';
  nombrePersonnes: number = 1;

  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private insuranceService: InsuranceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.clientId = this.route.snapshot.params['clientId'];
  }

  onSubmit() {
    let contract: any = {
      type: this.contractType,
      montantCotisation: this.montantCotisation,
      duree: this.duree,
      tauxCouverture: this.tauxCouverture
    };

    if (this.contractType === 'AUT') {
      contract = { ...contract, matricule: this.matricule, marque: this.marque, modele: this.modele };
    } else if (this.contractType === 'HAB') {
      contract = { ...contract, typeLogement: this.typeLogement, adresse: this.adresse, superficie: this.superficie };
    } else if (this.contractType === 'SAN') {
      contract = { ...contract, niveauCouverture: this.niveauCouverture, nombrePersonnes: this.nombrePersonnes };
    }

    this.insuranceService.saveContract(contract, this.clientId).subscribe({
      next: () => this.router.navigate(['/client-contracts', this.clientId]),
      error: (err) => this.errorMessage = "Erreur lors de la souscription"
    });
  }
}
