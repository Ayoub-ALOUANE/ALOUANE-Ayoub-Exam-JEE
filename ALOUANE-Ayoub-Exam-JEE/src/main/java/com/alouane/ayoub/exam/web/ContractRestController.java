package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.entities.Contract;
import com.alouane.ayoub.exam.entities.Payment;
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
    public List<Contract> getAllContracts() {
        return insuranceService.getAllContracts();
    }

    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable Long id) {
        return insuranceService.getContractById(id);
    }

    @PostMapping
    public Contract saveContract(@RequestBody Contract contract) {
        return insuranceService.saveContract(contract);
    }

    @GetMapping("/{id}/payments")
    public List<Payment> getPaymentsByContractId(@PathVariable Long id) {
        return insuranceService.getPaymentsByContractId(id);
    }

    @PostMapping("/{id}/payments")
    public Payment savePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Contract contract = insuranceService.getContractById(id);
        payment.setContract(contract);
        return insuranceService.savePayment(payment);
    }
}
