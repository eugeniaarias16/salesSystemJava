package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ProductDto;


import java.util.List;
import java.util.Map;

public interface IProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductByID(Long id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto upDateProductByID(Long id, ProductDto productDto);
    ProductDto updatePartial(Long id, Map<String, Object> updates);
    void deleteProduct(Long id);

}
