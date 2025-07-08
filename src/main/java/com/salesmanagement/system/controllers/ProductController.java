package com.salesmanagement.system.controllers;


import com.salesmanagement.system.DTO.ProductDto;
import com.salesmanagement.system.responses.ApiResponse;
import com.salesmanagement.system.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtoList= productService.getAllProducts();
        return ResponseEntity.ok(productDtoList);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto product= productService.getProductByID(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public  ResponseEntity<ProductDto> createProduct( @Valid @RequestBody ProductDto productDto){
        ProductDto createdProduct=productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> upDateProduct(@PathVariable Long id,
                                                    @Valid @RequestBody ProductDto productDto){
        ProductDto updatedProduct= productService.upDateProductByID(id,productDto);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully."));
    }

}
