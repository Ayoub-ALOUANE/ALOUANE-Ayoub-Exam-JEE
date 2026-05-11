package com.alouane.ayoub.exam.web;

import com.alouane.ayoub.exam.entities.Client;
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
    public List<Client> getAllClients() {
        return insuranceService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return insuranceService.getClientById(id);
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return insuranceService.saveClient(client);
    }
}
