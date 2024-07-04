package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.entity.Product;
import com.rangers.restaurantservice.entity.User;
import com.rangers.restaurantservice.mapper.ProductMapper;
import com.rangers.restaurantservice.repository.CategoryRepository;
import com.rangers.restaurantservice.repository.ProductRepository;
import com.rangers.restaurantservice.repository.UserRepository;
import com.rangers.restaurantservice.service.ProductService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ProductDto getProductById(ObjectId id) {
        Product product = productRepository.getProductByProductId(id);
        if (product==null) throw new NotFoundException("Product not found !!!");
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProductsAll() {
        return productMapper.toListDto(productRepository.findAll());
    }

    @Override
    public List<ProductDto> getProductsByName(String name) {
        return productMapper.toListDto(productRepository.getProductByNameContainsIgnoreCase(name));
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.getCategoryByName(categoryName);
        if (category==null) throw new NotFoundException("Category not found !!!");

        List<Product> products = productRepository.getProductsByCategory(category);
        return productMapper.toListDto(products);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        if (!productRepository.getProductByNameContainsIgnoreCase(productDto.getName()).isEmpty()) {
            throw new RuntimeException("Product with this name is already present !!!");
        }
        Product product = productMapper.toEntity(productDto);
        if (product==null) throw new NotFoundException("Error creating Product !!!");

        Category category = categoryRepository.getCategoryById(productDto.getCategoryId());
        product.setCategory(category);
        User user = userRepository.getUserByUserId(productDto.getUserId());
        product.setOwner(user);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ObjectId id, ProductDto productDto) {
        if (productDto.getName()!=null) {
            if (!productRepository.getProductByNameContainsIgnoreCase(productDto.getName()).isEmpty()) {
                throw new RuntimeException("Product with this name is already present !!!");
            }
        }
        Product product = productRepository.getProductByProductId(id);
        if (product==null) throw new NotFoundException("Product not found !!!");

        productMapper.update(productDto, product);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto activateDeactivateProduct(ObjectId id, boolean value) {
        Product product = productRepository.getProductByProductId(id);
        if (product==null) throw new NotFoundException("Product not found !!!");
        product.setIsActive(value);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(ObjectId categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found!", categoryId)));

        List<Product> products = productRepository.getProductsByCategoryId(categoryId);
        return productMapper.toListDto(products);
    }
}
