package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Address;
import com.salesmanagement.system.entities.AddressType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO representing an address associated with a client")
public class AddressDto {

    @Schema(description = "Unique identifier of the address", example = "1001")
    private Long id;

    @Schema(description = "ID of the associated client", example = "501")
    private Long clientId;

    @NotBlank
    @Schema(description = "Street name of the address", example = "Av. San Martín")
    private String street;

    @NotNull
    @Schema(description = "Type of the address", example = "HOME", implementation = AddressType.class)
    private AddressType addressType;

    @NotNull
    @Schema(description = "Street number of the address", example = "1234", minimum = "1")
    private int number;

    @Schema(description = "Floor number (optional)", example = "3")
    private int floor;

    @Schema(description = "Apartment (optional)", example = "B")
    private String apartment;

    @NotBlank
    @Pattern(regexp = "\\d{4,5}", message = "Invalid postal code")
    @Schema(description = "Postal code of the address", example = "5000")
    private String postalCode;

    @NotBlank
    @Schema(description = "Province of the address", example = "Córdoba")
    private String province;

    @NotBlank
    @Schema(description = "City of the address", example = "Córdoba")
    private String city;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.floor = address.getFloor();
        this.apartment = address.getApartment();
        this.postalCode = address.getPostalCode();
        this.province = address.getProvince();
        this.city = address.getCity();
        this.addressType = address.getAddressType();
        this.clientId = address.getClient() != null ? address.getClient().getId() : null;
    }

    public Address toEntity() {
        Address address = new Address();
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
