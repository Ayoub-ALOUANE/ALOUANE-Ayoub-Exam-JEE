package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.dtos.ContractDTO;
import com.alouane.ayoub.exam.dtos.PaymentDTO;
import com.alouane.ayoub.exam.enums.ContractStatus;
import com.alouane.ayoub.exam.services.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Contract API", description = "Endpoints for managing insurance contracts and payments")
public class ContractRestController {
    private final InsuranceService insuranceService;

    @GetMapping
    @Operation(summary = "Get all contracts")
    public List<ContractDTO> getAllContracts() {
        return insuranceService.getAllContracts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contract by ID")
    public ContractDTO getContractById(@PathVariable Long id) {
        return insuranceService.getContractById(id);
    }

    @PostMapping("/client/{clientId}")
    @Operation(summary = "Create a new contract for a client")
    public ContractDTO saveContract(@RequestBody ContractDTO contractDTO, @PathVariable Long clientId) {
        return insuranceService.saveContract(contractDTO, clientId);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Change contract status (VALIDE, RESILIE, etc.)")
    public void changeStatus(@PathVariable Long id, @RequestParam ContractStatus status) {
        insuranceService.changeContractStatus(id, status);
    }

    @GetMapping("/{id}/payments")
    @Operation(summary = "Get all payments for a contract")
    public List<PaymentDTO> getPaymentsByContractId(@PathVariable Long id) {
        return insuranceService.getPaymentsByContractId(id);
    }

    @PostMapping("/{id}/payments")
    @Operation(summary = "Add a payment to a contract")
    public PaymentDTO addPayment(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        return insuranceService.addPaymentToContract(id, paymentDTO);
    }

    @GetMapping("/{id}/payments/total")
    @Operation(summary = "Calculate total payments for a contract")
    public Double getTotalPayments(@PathVariable Long id) {
        return insuranceService.calculateTotalPaymentsByContract(id);
    }
}
