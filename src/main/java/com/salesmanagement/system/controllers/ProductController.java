package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.ProductDto;
import com.salesmanagement.system.responses.CustomApiResponse;
import com.salesmanagement.system.services.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Operations related to products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Returns a list of all products.")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productService.getAllProducts();
        return ResponseEntity.ok(productDtoList);
    }

    @Operation(summary = "Get product by ID", description = "Returns a single product by its ID.")
    @ApiResponse(responseCode = "200", description = "Product retrieved successfully")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "ID of the product to retrieve") @PathVariable Long id) {
        ProductDto product = productService.getProductByID(id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Create a new product", description = "Creates a new product with the provided data.")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @Valid @RequestBody ProductDto productDto) {
        ProductDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @Operation(summary = "Update a product by ID", description = "Updates the data of an existing product.")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> upDateProduct(
            @Parameter(description = "ID of the product to update") @PathVariable Long id,
            @Valid @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.upDateProductByID(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updatePartialProduct(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        ProductDto updatedProduct = productService.updatePartial(id, updates);
        return ResponseEntity.ok(updatedProduct);
    }



    @Operation(summary = "Delete a product by ID", description = "Deletes the product with the given ID.")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse> deleteProduct(
            @Parameter(description = "ID of the product to delete") @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new CustomApiResponse("Product deleted successfully."));
    }
}
