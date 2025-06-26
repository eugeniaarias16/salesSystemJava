package com.salesmanagement.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String color;

    private String size;

    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Sale>sales= new ArrayList<>();

    //Business Methods for Inventory Management
    public  void increaseStock(int amount){
        this.stock+=amount;
    }
    public void decreaseStock(int amount){
        if(amount>this.stock) throw new IllegalArgumentException("Insufficient stock");
        this.stock-=amount;
    }


}
