package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.AddressDto;
import com.salesmanagement.system.DTO.ClientDto;
import com.salesmanagement.system.entities.Address;
import com.salesmanagement.system.entities.AddressType;
import com.salesmanagement.system.entities.Client;
import com.salesmanagement.system.exceptions.ResourceNotFoundException;
import com.salesmanagement.system.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService{

    private final AddressRepository addressRepository;
    private final IClientService clientService;


    public AddressService(AddressRepository addressRepository, IClientService clientService) {
        this.addressRepository = addressRepository;
        this.clientService=clientService;
    }

    @Override
    public List<AddressDto> getAllAddressess() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long id) {
        Address address= addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address with the id "+id+" was not found"));
        return new AddressDto(address);
    }

    @Override
    public AddressDto createAddress(Long clientId, AddressDto addressDto) {
        ClientDto existingClient= clientService.getClientById(clientId);
        Client client=existingClient.toEntity();

        Address address= addressDto.toEntity(); //We convert AddressDto into entity(Address).
        address.setClient(client); //Associate the client
        Address savedAddress= addressRepository.save(address);

        return new AddressDto(savedAddress); // return as Dto


    }

    @Override
    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        // Search for existing address or throw exception
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address with the id " + id + " was not found."));

        // Update the fields
        existingAddress.setAddressType(addressDto.getAddressType());
        existingAddress.setStreet(addressDto.getStreet());
        existingAddress.setFloor(addressDto.getFloor());
        existingAddress.setApartment(addressDto.getApartment());
        existingAddress.setProvince(addressDto.getProvince());
        existingAddress.setCity(addressDto.getCity());
        existingAddress.setNumber(addressDto.getNumber());
        existingAddress.setPostalCode(addressDto.getPostalCode());

        // Save the updated address
        Address updatedAddress = addressRepository.save(existingAddress);

        // Return the updated DTO
        return new AddressDto(updatedAddress);
    }


    @Override
    public void deleteAddress(Long id) {
        Address existingAddress= addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address not found with the id: "+id));

        addressRepository.delete(existingAddress);
    }

    @Override
    public List<AddressDto> getAddressByClient(Long clientId) {
        //Filter address by Client ID
        List<Address> addressList= addressRepository.findByClientId(clientId);

        //Convert to AddressDto
        return addressList.stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> getAddressByCity(String city) {
        List<Address> addressList= addressRepository.findByCityIgnoreCase(city);

        return addressList.stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> getAddressByClientAndType(Long idClient, AddressType addressType) {
        List<Address>addressList=addressRepository.findByClientIdAndAddressType(idClient,addressType);

        return addressList.stream()
                .map(AddressDto::new)
                .collect(Collectors.toList());// If it does not exist, returns an empty list.
    }
}
