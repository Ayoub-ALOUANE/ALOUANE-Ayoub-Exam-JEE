package com.alouane.ayoub.exam.services;

import com.alouane.ayoub.exam.entities.Client;
import com.alouane.ayoub.exam.entities.Contract;
import com.alouane.ayoub.exam.entities.Payment;

import java.util.List;

public interface InsuranceService {
    Client saveClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long id);
    
    Contract saveContract(Contract contract);
    List<Contract> getAllContracts();
    Contract getContractById(Long id);
    
    Payment savePayment(Payment payment);
    List<Payment> getPaymentsByContractId(Long contractId);
}
