package com.alouane.ayoub.exam.mappers;

import com.alouane.ayoub.exam.dtos.*;
import com.alouane.ayoub.exam.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InsuranceMapper {

    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    public ContractDTO fromContract(Contract contract) {
        if (contract instanceof AutomobileContract) {
            AutomobileContractDTO dto = new AutomobileContractDTO();
            BeanUtils.copyProperties(contract, dto);
            dto.setType("AUT");
            if (contract.getClient() != null) dto.setClient(fromClient(contract.getClient()));
            return dto;
        } else if (contract instanceof HabitationContract) {
            HabitationContractDTO dto = new HabitationContractDTO();
            BeanUtils.copyProperties(contract, dto);
            dto.setType("HAB");
            if (contract.getClient() != null) dto.setClient(fromClient(contract.getClient()));
            return dto;
        } else if (contract instanceof HealthContract) {
            HealthContractDTO dto = new HealthContractDTO();
            BeanUtils.copyProperties(contract, dto);
            dto.setType("SAN");
            if (contract.getClient() != null) dto.setClient(fromClient(contract.getClient()));
            return dto;
        }
        return null;
    }

    public Contract fromContractDTO(ContractDTO contractDTO) {
        if (contractDTO instanceof AutomobileContractDTO) {
            AutomobileContract contract = new AutomobileContract();
            BeanUtils.copyProperties(contractDTO, contract);
            if (contractDTO.getClient() != null) contract.setClient(fromClientDTO(contractDTO.getClient()));
            return contract;
        } else if (contractDTO instanceof HabitationContractDTO) {
            HabitationContract contract = new HabitationContract();
            BeanUtils.copyProperties(contractDTO, contract);
            if (contractDTO.getClient() != null) contract.setClient(fromClientDTO(contractDTO.getClient()));
            return contract;
        } else if (contractDTO instanceof HealthContractDTO) {
            HealthContract contract = new HealthContract();
            BeanUtils.copyProperties(contractDTO, contract);
            if (contractDTO.getClient() != null) contract.setClient(fromClientDTO(contractDTO.getClient()));
            return contract;
        }
        return null;
    }

    public PaymentDTO fromPayment(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(payment, paymentDTO);
        return paymentDTO;
    }

    public Payment fromPaymentDTO(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);
        return payment;
    }
}
