package com.salesmanagement.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private long dni;
    private LocalDate birthDate;

    //Existing relationships with other entities
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses= new ArrayList<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Sale> sales= new ArrayList<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Receipt>receipts=new ArrayList<>();

    //Convenience methods for handling addresses
    public void addAddress(Address address){
        addresses.add(address);
        address.setClient(this); //synchronize both sides of the relation
    }
    public void removeAddress(Address address){
        addresses.remove(address);
        address.setClient(null);//breaks the association between that address and the customer
    }
}
