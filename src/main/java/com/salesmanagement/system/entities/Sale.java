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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sale_product", // Intermediate table name
            joinColumns = @JoinColumn(name="sale_id"), //FK referring to the current table (salts)
            inverseJoinColumns = @JoinColumn(name="product_id") //FK referring to the related table (products)
    )
    private List<Product>products=new ArrayList<>();



}
