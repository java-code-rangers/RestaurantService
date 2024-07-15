package com.rangers.restaurantservice.apprunner.impl;

import com.rangers.restaurantservice.apprunner.util.DataCreator;
import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.repository.ProductRepository;
import com.rangers.restaurantservice.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.bson.types.ObjectId;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getProductAll() {
        List<ProductDto> productDtoActual = productService.getProductsAll();
        Assertions.assertEquals(3, productDtoActual.size());
    }

    @Test
    void getProductById() {
        ProductDto productDtoExpected = DataCreator.getProductDtoOne();
        ProductDto productDtoActual = productService.getProductById(new ObjectId("66842a43c995c63518773a6f"));
        Assertions.assertEquals(productDtoExpected, productDtoActual);
    }

    @Test
    void getProductByName() {
        ProductDto productDtoExpected = DataCreator.getProductDtoOne();
        List<ProductDto> productDtoActual = productService.getProductsByName("Торт ванильный");
        Assertions.assertEquals(productDtoExpected, productDtoActual.getFirst());
    }

    @Test
    void getProductsByCategoryName() {
        ProductDto productDtoExpected = DataCreator.getProductDtoOne();
        List<ProductDto> productDtoActual = productService.getProductsByCategoryName("cake");
        Assertions.assertEquals(2, productDtoActual.size());
        Assertions.assertEquals(productDtoExpected, productDtoActual.getFirst());
    }

    @Test
    void createProduct() {
        ProductDto testDto = DataCreator.getProductDtoTwo();
        ProductDto productDtoActual = productService.createProduct(testDto);
        testDto.setProductId(productDtoActual.getProductId());
        Assertions.assertEquals(testDto, productDtoActual);
        productRepository.deleteById(testDto.getProductId());
        Assertions.assertTrue(productRepository.findById(testDto.getProductId()).isEmpty());
    }

    @Test
    void updateProduct() {
        ProductDto changeDto = DataCreator.getProductDtoThree();
        ProductDto productDtoActual = productService.updateProduct(changeDto.getProductId(),changeDto);
        Assertions.assertEquals(changeDto, productDtoActual);

        ProductDto oldDto = DataCreator.getProductDtoOne();
        ProductDto productOldDtoActual = productService.updateProduct(oldDto.getProductId(),oldDto);
        Assertions.assertEquals(oldDto, productOldDtoActual);
    }

    @Test
    void getProductsByCategoryId() {
        ProductDto productDtoExpected = DataCreator.getProductDtoOne();
        List<ProductDto> productDtoActual = productService.getProductsByCategoryId(
                new ObjectId("6685d4d6d204885cfc728065"));
        Assertions.assertEquals(2, productDtoActual.size());
        Assertions.assertEquals(productDtoExpected, productDtoActual.getFirst());
    }
}
