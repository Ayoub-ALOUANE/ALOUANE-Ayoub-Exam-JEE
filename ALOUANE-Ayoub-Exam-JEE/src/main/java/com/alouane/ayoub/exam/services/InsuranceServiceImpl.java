package com.alouane.ayoub.exam.services;

import com.alouane.ayoub.exam.dtos.*;
import com.alouane.ayoub.exam.entities.*;
import com.alouane.ayoub.exam.enums.ContractStatus;
import com.alouane.ayoub.exam.mappers.InsuranceMapper;
import com.alouane.ayoub.exam.repositories.ClientRepository;
import com.alouane.ayoub.exam.repositories.ContractRepository;
import com.alouane.ayoub.exam.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final InsuranceMapper mapper;

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = mapper.fromClientDTO(clientDTO);
        return mapper.fromClient(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(mapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        return mapper.fromClient(client);
    }

    @Override
    public ContractDTO saveContract(ContractDTO contractDTO, Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Contract contract = mapper.fromContractDTO(contractDTO);
        contract.setClient(client);
        contract.setDateSouscription(LocalDate.now());
        contract.setStatut(ContractStatus.EN_COURS);
        return mapper.fromContract(contractRepository.save(contract));
    }

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(mapper::fromContract)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
        return mapper.fromContract(contract);
    }

    @Override
    public List<ContractDTO> getContractsByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getContrats().stream()
                .map(mapper::fromContract)
                .collect(Collectors.toList());
    }

    @Override
    public void changeContractStatus(Long contractId, ContractStatus status) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.setStatut(status);
        if (status == ContractStatus.VALIDE) {
            contract.setDateValidation(LocalDate.now());
        }
        contractRepository.save(contract);
    }

    @Override
    public PaymentDTO addPaymentToContract(Long contractId, PaymentDTO paymentDTO) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException("Contract not found"));
        Payment payment = mapper.fromPaymentDTO(paymentDTO);
        payment.setContract(contract);
        payment.setDate(LocalDate.now());
        return mapper.fromPayment(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentDTO> getPaymentsByContractId(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException("Contract not found"));
        return contract.getPaiements().stream()
                .map(mapper::fromPayment)
                .collect(Collectors.toList());
    }

    @Override
    public Double calculateTotalPaymentsByContract(Long contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new RuntimeException("Contract not found"));
        return contract.getPaiements().stream()
                .mapToDouble(Payment::getMontant)
                .sum();
    }
}
