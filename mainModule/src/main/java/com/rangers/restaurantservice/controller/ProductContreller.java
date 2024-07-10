package com.rangers.restaurantservice.controller;

import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductContreller {

    private final ProductService productService;

    @GetMapping("/all")
    //http://localhost:8080/product/all
    public List<ProductDto> getProductAll() {
        return productService.getProductsAll();
    }

    @GetMapping("/{id}")
    //http://localhost:8080/product/...
    public ProductDto getProductById(@PathVariable("id") ObjectId id) {
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    //http://localhost:8080/product/name/...
    public List<ProductDto> getProductByName(@PathVariable("name") String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/category-name/{nameCategory}")
    //http://localhost:8080/product/category-name/...
    public List<ProductDto> getProductsByCategoryName(@PathVariable("nameCategory") String nameCategory) {
        return productService.getProductsByCategoryName(nameCategory);
    }

    @PostMapping("/create")
    //http://localhost:8080/product/create
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/update/{id}")
    //http://localhost:8080/product/update/{id}
    public ProductDto updateProduct(@PathVariable("id") ObjectId id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @GetMapping("/category-id/{idCategory}")
    //http://localhost:8080/product/category-id/...
    public List<ProductDto> getProductsByCategoryId(@PathVariable("idCategory") ObjectId idCategory) {
        return productService.getProductsByCategoryId(idCategory);
    }
}
