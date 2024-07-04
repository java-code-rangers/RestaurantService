package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.entity.Product;
import com.rangers.restaurantservice.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Product getProductByProductId(String id);
    List<Product> getProductByNameContainsIgnoreCase(String name);
    List<Product> getProductsByCategory(Category category);
}
