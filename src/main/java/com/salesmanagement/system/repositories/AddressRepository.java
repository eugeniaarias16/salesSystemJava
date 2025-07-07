package com.salesmanagement.system.repositories;

import com.salesmanagement.system.entities.Address;
import com.salesmanagement.system.entities.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    //Custom Methods

    // Find all addresses for a client
    List<Address> findByClientId(Long clientId);

    // Find a specific address for a client by type
    List <Address> findByClientIdAndAddressType(Long clientId, AddressType addressType);

    // Check if a customer has an address of a certain type
    boolean existsByClientIdAndAddressType(Long clientId, AddressType addressType);

    // Search for addresses by city
    List<Address> findByCityIgnoreCase(String city);

    // Search for addresses by province
    List<Address> findByProvinceIgnoreCase(String province);

    // Search for addresses by postal code
    List<Address> findByPostalCode(String postalCode);

    // Search for addresses by type
    List<Address> findByAddressType(AddressType addressType);


    // Check if a client has addresses
    boolean existsByClientId(Long clientId);

    // Count addresses for a client
    long countByClientId(Long clientId);
}
