package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data Transfer Object for Product")
public class ProductDto {

    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "Product's full name", example = "Soccer ball")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Product description", example = "High quality size 5 soccer ball.")
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @Schema(description = "Available stock units", example = "100", minimum = "0")
    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @Schema(description = "Unit price of the product", example = "19.99", minimum = "0")
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

    @Schema(description = "Color of the product", example = "Red/Black")
    private String color;

    @Schema(description = "Available sizes", example = "S,M,L,XL")
    private String sizes;

    public ProductDto(Product product){
        if(product != null){
            this.id = product.getId();
            this.name = product.getName();
            this.description = product.getDescription();
            this.stock = product.getStock();
            this.price = product.getPrice();
            this.color = product.getColor();
            this.sizes = product.getSize();
        }
    }

    public Product toEntity(){
        Product product = new Product();
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
