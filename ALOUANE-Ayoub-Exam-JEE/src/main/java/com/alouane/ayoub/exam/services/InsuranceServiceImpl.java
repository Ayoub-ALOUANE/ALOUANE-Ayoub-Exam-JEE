package com.alouane.ayoub.exam.services;

import com.alouane.ayoub.exam.entities.Client;
import com.alouane.ayoub.exam.entities.Contract;
import com.alouane.ayoub.exam.entities.Payment;
import com.alouane.ayoub.exam.repositories.ClientRepository;
import com.alouane.ayoub.exam.repositories.ContractRepository;
import com.alouane.ayoub.exam.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByContractId(Long contractId) {
        // Simple implementation, could be optimized with a custom query in repository
        return contractRepository.findById(contractId)
                .map(Contract::getPaiements)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }
}
