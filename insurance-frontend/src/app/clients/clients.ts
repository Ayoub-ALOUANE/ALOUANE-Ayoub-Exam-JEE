import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InsuranceService } from '../services/insurance';
import { Client } from '../models/model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './clients.html',
  styleUrl: './clients.css'
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  errorMessage = '';

  constructor(private insuranceService: InsuranceService) {}

  ngOnInit(): void {
    this.insuranceService.getClients().subscribe({
      next: (data) => {
        this.clients = data;
      },
      error: (err) => {
        this.errorMessage = "Erreur lors du chargement des clients";
      }
    });
  }
}
