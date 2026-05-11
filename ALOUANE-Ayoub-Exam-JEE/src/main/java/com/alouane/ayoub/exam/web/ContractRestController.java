package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.dtos.ContractDTO;
import com.alouane.ayoub.exam.dtos.PaymentDTO;
import com.alouane.ayoub.exam.enums.ContractStatus;
import com.alouane.ayoub.exam.services.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContractRestController {
    private final InsuranceService insuranceService;

    @GetMapping
    public List<ContractDTO> getAllContracts() {
        return insuranceService.getAllContracts();
    }

    @GetMapping("/{id}")
    public ContractDTO getContractById(@PathVariable Long id) {
        return insuranceService.getContractById(id);
    }

    @PostMapping("/client/{clientId}")
    public ContractDTO saveContract(@RequestBody ContractDTO contractDTO, @PathVariable Long clientId) {
        return insuranceService.saveContract(contractDTO, clientId);
    }

    @PatchMapping("/{id}/status")
    public void changeStatus(@PathVariable Long id, @RequestParam ContractStatus status) {
        insuranceService.changeContractStatus(id, status);
    }

    @GetMapping("/{id}/payments")
    public List<PaymentDTO> getPaymentsByContractId(@PathVariable Long id) {
        return insuranceService.getPaymentsByContractId(id);
    }

    @PostMapping("/{id}/payments")
    public PaymentDTO addPayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        return insuranceService.addPaymentToContract(id, paymentDTO);
    }

    @GetMapping("/{id}/payments/total")
    public Double getTotalPayments(@PathVariable Long id) {
        return insuranceService.calculateTotalPaymentsByContract(id);
    }
}
