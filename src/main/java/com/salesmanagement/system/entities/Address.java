package com.salesmanagement.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Address {

    @Id
    @GeneratedValue()
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name="client_id")
    private Client client;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String apartment;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType addressType;

    //Constructor without client
    public Address(Long id, String street, int number, int floor, String apartment, String postalCode, String province, String city, AddressType addressType) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.apartment = apartment;
        this.postalCode = postalCode;
        this.province = province;
        this.city = city;
        this.addressType = addressType;
    }
}
