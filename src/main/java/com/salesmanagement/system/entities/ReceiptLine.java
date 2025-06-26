package com.salesmanagement.system.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReceiptLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double lineTotal; //price*total

    // Compare if two ReceiptLine objects are equal in content
    @Override
    public boolean equals(Object object) {

        if (this == object) return true; //if they are exactly the same instance in memory.
        if (object == null || this.getClass() != object.getClass()) return false; //If the object received is null, or is not of the same exact class

        ReceiptLine that=(ReceiptLine) object; //explicit cast

        //compare the relevant attributes
        return quantity== that.quantity &&
                Double.compare(that.lineTotal,lineTotal)==0 &&
                Objects.equals(that.id,id) &&
                Objects.equals(that.product,product);
    }




}
