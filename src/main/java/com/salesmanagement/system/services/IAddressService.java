package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.AddressDto;
import com.salesmanagement.system.entities.AddressType;

import java.util.List;


public interface IAddressService {

    //Basic CRUD
    List<AddressDto> getAllAddressess();
    AddressDto getAddressById(Long id);
    AddressDto createAddress(Long clientId, AddressDto addressDto);
    AddressDto updateAddress(Long id, AddressDto addressDto);
    void deleteAddress(Long id);

    //Specific methods
    List<AddressDto> getAddressByClient(Long clientId);
    List<AddressDto> getAddressByCity(String city);
    List<AddressDto> getAddressByClientAndType(Long idClient, AddressType addressType);

}
