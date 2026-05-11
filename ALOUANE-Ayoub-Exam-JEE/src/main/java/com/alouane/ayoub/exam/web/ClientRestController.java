package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.dtos.ClientDTO;
import com.alouane.ayoub.exam.dtos.ContractDTO;
import com.alouane.ayoub.exam.services.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientRestController {
    private final InsuranceService insuranceService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return insuranceService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return insuranceService.getClientById(id);
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return insuranceService.saveClient(clientDTO);
    }

    @GetMapping("/{id}/contracts")
    public List<ContractDTO> getClientContracts(@PathVariable Long id) {
        return insuranceService.getContractsByClientId(id);
    }
}
