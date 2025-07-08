package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ProductDto;


import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductByID(Long id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto upDateProductByID(Long id, ProductDto productDto);
    void deleteProduct(Long id);

}
