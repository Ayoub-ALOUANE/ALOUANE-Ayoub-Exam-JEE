package com.alouane.ayoub.exam.services;

import com.alouane.ayoub.exam.dtos.*;
import com.alouane.ayoub.exam.enums.ContractStatus;

import java.util.List;

public interface InsuranceService {
    // Clients
    ClientDTO saveClient(ClientDTO clientDTO);
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);

    // Contracts
    ContractDTO saveContract(ContractDTO contractDTO, Long clientId);
    List<ContractDTO> getAllContracts();
    ContractDTO getContractById(Long id);
    List<ContractDTO> getContractsByClientId(Long clientId);
    void changeContractStatus(Long contractId, ContractStatus status);

    // Payments
    PaymentDTO addPaymentToContract(Long contractId, PaymentDTO paymentDTO);
    List<PaymentDTO> getPaymentsByContractId(Long contractId);
    Double calculateTotalPaymentsByContract(Long contractId);
}

