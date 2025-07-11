package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.AddressDto;
import com.salesmanagement.system.entities.AddressType;
import com.salesmanagement.system.responses.CustomApiResponse;
import com.salesmanagement.system.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Address Controller", description = "Operations related to client addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Get all addresses", description = "Returns a list of all addresses.")
    @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully")
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addressDtoList = addressService.getAllAddressess();
        return ResponseEntity.ok(addressDtoList);
    }

    @Operation(summary = "Get addresses by client ID", description = "Returns all addresses for a given client.")
    @ApiResponse(responseCode = "200", description = "Client addresses retrieved successfully")
    @GetMapping("/clients/{id}/addresses")
    public ResponseEntity<List<AddressDto>> getAddressesByClientID(
            @Parameter(description = "Client ID") @PathVariable Long id) {
        List<AddressDto> addressDtoList = addressService.getAddressByClient(id);
        return ResponseEntity.ok(addressDtoList);
    }

    @Operation(summary = "Get addresses by client ID and type", description = "Returns client addresses filtered by address type.")
    @ApiResponse(responseCode = "200", description = "Filtered addresses retrieved successfully")
    @GetMapping("/clients/{id}/addresses/{type}")
    public ResponseEntity<List<AddressDto>> getAddressByClientAndByType(
            @Parameter(description = "Client ID") @PathVariable Long id,
            @Parameter(description = "Type of address") @PathVariable AddressType type) {
        List<AddressDto> addressDtoList = addressService.getAddressByClientAndType(id, type);
        return ResponseEntity.ok(addressDtoList);
    }

    @Operation(summary = "Get addresses by city", description = "Returns all addresses located in a given city.")
    @ApiResponse(responseCode = "200", description = "Addresses in city retrieved successfully")
    @GetMapping("/addresses/city/{city}")
    public ResponseEntity<List<AddressDto>> getAddressesByCity(
            @Parameter(description = "City name") @PathVariable String city) {
        List<AddressDto> addressDtoList = addressService.getAddressByCity(city);
        return ResponseEntity.ok(addressDtoList);
    }

    @Operation(summary = "Create a new address for a client", description = "Creates and assigns an address to a specific client.")
    @ApiResponse(responseCode = "201", description = "Address created successfully")
    @PostMapping("/clients/{id}/addresses")
    public ResponseEntity<AddressDto> createAddress(
            @Parameter(description = "Client ID") @PathVariable Long id,
            @Valid @RequestBody AddressDto addressDto) {
        AddressDto createdAddress = addressService.createAddress(id, addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @Operation(summary = "Update address by ID", description = "Updates an existing address.")
    @ApiResponse(responseCode = "200", description = "Address updated successfully")
    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressDto> upDateAddress(
            @Parameter(description = "Address ID") @PathVariable Long id,
            @Valid @RequestBody AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    @Operation(summary = "Delete address by ID", description = "Deletes the address with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Address deleted successfully")
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<CustomApiResponse> deleteAddress(
            @Parameter(description = "Address ID") @PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok(new CustomApiResponse("Address deleted successfully."));
    }
}
