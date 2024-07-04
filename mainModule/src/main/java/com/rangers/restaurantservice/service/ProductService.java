package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.dto.UserDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(String id);
    List<ProductDto> getProductsAll();
    List<ProductDto> getProductsByName(String name);
    List<ProductDto> getProductsByCategory(String category);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(String id, ProductDto productDto);
    ProductDto activateDeactivateProduct(String id, boolean value);
}
