package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    private String color;

    private String sizes;

    public ProductDto(Product product){
        if(product!=null){
            this.id= product.getId();
            this.name=product.getName();
            this.description=product.getDescription();
            this.stock= product.getStock();
            this.price= product.getPrice();
            this.color= product.getColor();
            this.sizes= product.getSize();
        }
    }

    public Product toEntity(){
        Product product= new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStock(this.stock);
        product.setColor(this.color);
        product.setSize(this.sizes);
        return product;
    }



}
