package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Address;
import com.salesmanagement.system.entities.AddressType;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdressDto {
    
    private Long id;

    private Long clientId; //we receive client as a reference

    @NotBlank
    private String street;

    @NotNull
    private AddressType addressType;

    @NotBlank
    private  int number;

    private int floor;

    private String apartment;
    
    @Pattern(regexp = "\\d{4,5}", message = "Invalid postal code")
    @NotBlank
    private String postalCode;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    public AdressDto(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.floor = address.getFloor();
        this.apartment = address.getApartment();
        this.postalCode = address.getPostalCode();
        this.province = address.getProvince();
        this.city = address.getCity();
        this.addressType = address.getAddressType();
        this.clientId= address.getClient()!=null?address.getClient().getId():null;
    }

    public Address toEntity(){
        Address address= new Address();
        address.setId(this.id);
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setFloor(this.floor);
        address.setApartment(this.apartment);
        address.setProvince(this.province);
        address.setPostalCode(this.postalCode);
        address.setAddressType(this.addressType);
        address.setCity(this.city);
        return address;
    }

}
