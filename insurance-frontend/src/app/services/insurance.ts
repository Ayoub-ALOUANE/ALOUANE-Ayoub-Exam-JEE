import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client, Contract, Payment } from '../models/model';

@Injectable({
  providedIn: 'root'
})
export class InsuranceService {
  private host: string = "http://localhost:8082/api";

  constructor(private http: HttpClient) { }

  public getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.host}/clients`);
  }

  public getClientContracts(clientId: number): Observable<Contract[]> {
    return this.http.get<Contract[]>(`${this.host}/clients/${clientId}/contracts`);
  }

  public saveContract(contract: any, clientId: number): Observable<Contract> {
    return this.http.post<Contract>(`${this.host}/contracts/client/${clientId}`, contract);
  }

  public validateContract(contractId: number): Observable<any> {
    // Backend uses PATCH /api/contracts/{id}/status?status=VALIDE
    return this.http.patch(`${this.host}/contracts/${contractId}/status?status=VALIDE`, {});
  }

  public getContractPayments(contractId: number): Observable<Payment[]> {
    return this.http.get<Payment[]>(`${this.host}/contracts/${contractId}/payments`);
  }

  public addPayment(contractId: number, payment: Payment): Observable<Payment> {
    return this.http.post<Payment>(`${this.host}/contracts/${contractId}/payments`, payment);
  }
}
