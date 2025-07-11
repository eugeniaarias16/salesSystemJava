package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.ClientDto;
import com.salesmanagement.system.responses.CustomApiResponse;
import com.salesmanagement.system.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/clients")
@Tag(name = "Client Controller", description = "Operations related to clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Get all clients", description = "Returns a list of all registered clients.")
    @ApiResponse(responseCode = "200", description = "Clients retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Get client by ID", description = "Returns the client with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Client retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(
            @Parameter(description = "Client ID") @PathVariable Long id) {
        ClientDto client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @Operation(summary = "Create a new client", description = "Creates a new client.")
    @ApiResponse(responseCode = "201", description = "Client created successfully")
    @PostMapping("/create")
    public ResponseEntity<ClientDto> createClient(
            @Valid @RequestBody ClientDto clientDto) {
        ClientDto savedClient = clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @Operation(summary = "Update a client", description = "Updates the client with the given ID.")
    @ApiResponse(responseCode = "200", description = "Client updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(
            @Parameter(description = "Client ID") @PathVariable Long id,
            @Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @Operation(summary = "Partially update a client by ID", description = "Updates specific fields of a client.")
    @ApiResponse(responseCode = "200", description = "Client updated successfully")
    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> partiallyUpdateClient(
            @Parameter(description = "ID of the client to update") @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        ClientDto updatedClient = clientService.partiallyUpdateClient(id, updates);
        return ResponseEntity.ok(updatedClient);
    }

    @Operation(summary = "Delete a client", description = "Deletes the client with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Client deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse> deleteClient(
            @Parameter(description = "Client ID") @PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok(new CustomApiResponse("Client deleted successfully"));
    }
}
