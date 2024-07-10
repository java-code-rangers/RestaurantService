package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.ProductDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(ObjectId id);
    List<ProductDto> getProductsAll();
    List<ProductDto> getProductsByName(String name);
    List<ProductDto> getProductsByCategoryName(String category);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ObjectId id, ProductDto productDto);
    ProductDto activateDeactivateProduct(ObjectId id, boolean value);
    List<ProductDto> getProductsByCategoryId(ObjectId id);
}
