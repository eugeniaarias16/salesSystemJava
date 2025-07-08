package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ProductDto;
import com.salesmanagement.system.entities.Product;
import com.salesmanagement.system.exceptions.ResourceNotFoundException;
import com.salesmanagement.system.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

   private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return  productRepository.findAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductByID(Long id) {
       Product existingProduct= productRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Product with ID "+id+" was not found."));
       return new ProductDto(existingProduct);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product= productDto.toEntity();
        Product savedProduct= productRepository.save(product);
        return new ProductDto(savedProduct);
    }

    @Override
    public ProductDto upDateProductByID(Long id, ProductDto productDto) {
        Product existingProduct= productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product with ID "+id+" was not found."));
        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setColor(productDto.getColor());
        existingProduct.setSize(productDto.getSizes());

        Product savedProduct=productRepository.save(existingProduct);
        return new ProductDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
       if(!productRepository.existsById(id)){
       throw new ResourceNotFoundException("Product with ID "+id+" was not found.");
       }
       productRepository.deleteById(id);
    }
}
