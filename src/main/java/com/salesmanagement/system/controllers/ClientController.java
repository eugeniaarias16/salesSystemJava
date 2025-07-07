package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.ClientDto;
import com.salesmanagement.system.responses.ApiResponse;
import com.salesmanagement.system.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clients= clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}" )
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){
        ClientDto client= clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto){
        ClientDto savedClient= clientService.createClient(clientDto);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id,
                                                  @Valid @RequestBody ClientDto clientDto){
        ClientDto updatedClient= clientService.updateClient(id,clientDto);
        return ResponseEntity.ok(updatedClient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable Long id){
        clientService.deleteClientById(id);
        return ResponseEntity.ok(new ApiResponse("Client deleted successfully"));
    }
}
