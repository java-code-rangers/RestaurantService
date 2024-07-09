package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepositoryMock;

    @Test
    void getProductById() {
    }
}