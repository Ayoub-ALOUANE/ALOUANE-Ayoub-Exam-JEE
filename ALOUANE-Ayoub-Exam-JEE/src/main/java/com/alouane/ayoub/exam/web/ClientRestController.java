package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.dtos.ClientDTO;
import com.alouane.ayoub.exam.dtos.ContractDTO;
import com.alouane.ayoub.exam.services.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Client API", description = "Endpoints for managing clients")
public class ClientRestController {
    private final InsuranceService insuranceService;

    @GetMapping
    @Operation(summary = "Get all clients")
    public List<ClientDTO> getAllClients() {
        return insuranceService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID")
    public ClientDTO getClientById(@PathVariable Long id) {
        return insuranceService.getClientById(id);
    }

    @PostMapping
    @Operation(summary = "Save a new client")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return insuranceService.saveClient(clientDTO);
    }

    @GetMapping("/{id}/contracts")
    @Operation(summary = "Get all contracts for a specific client")
    public List<ContractDTO> getClientContracts(@PathVariable Long id) {
        return insuranceService.getContractsByClientId(id);
    }
}
