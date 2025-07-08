package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.AddressDto;
import com.salesmanagement.system.entities.AddressType;
import com.salesmanagement.system.responses.ApiResponse;
import com.salesmanagement.system.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

   private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /* ---------------- GET METHODS ---------------- */

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDto>> getAllAddresses(){
        List<AddressDto> addressDtoList= addressService.getAllAddressess();
        return ResponseEntity.ok(addressDtoList);
    }

    @GetMapping("/clients/{id}/addresses")
    public ResponseEntity<List<AddressDto>> getAddressesByClientID(@PathVariable Long id){
        List<AddressDto> addressDtoList= addressService.getAddressByClient(id);
        return ResponseEntity.ok(addressDtoList);
    }

    @GetMapping("/clients/{id}/addresses/{type}")
    public ResponseEntity<List<AddressDto>> getAddressByClientAndByType(@PathVariable Long id, @PathVariable AddressType type){
        List<AddressDto>addressDtoList= addressService.getAddressByClientAndType(id,type);
        return ResponseEntity.ok(addressDtoList);

    }

    @GetMapping("/addresses/city/{city}")
    public ResponseEntity<List<AddressDto>> getAddressesByCity(@PathVariable String city){
        List<AddressDto> addressDtoList= addressService.getAddressByCity(city);
        return ResponseEntity.ok(addressDtoList);
    }



    /* ---------------- POST METHODS ---------------- */

    @PostMapping("/clients/{id}/addresses")
    public ResponseEntity<AddressDto> createAddress(@PathVariable Long id,
                                                    @Valid @RequestBody AddressDto addressDto){
        AddressDto createdAddress= addressService.createAddress(id,addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    /* ---------------- PUT METHODS ---------------- */
    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressDto> upDateAddress(@PathVariable Long id,
                                                    @Valid @RequestBody AddressDto addressDto){
        AddressDto updatedAddress= addressService.updateAddress(id,addressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    /* ---------------- DELETE METHODS ---------------- */
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok(new ApiResponse("Address deleted successfully."));
    }
}
