package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Category getCategoryByName(String categoryName);
    Category getCategoryById(ObjectId id);
    Optional<Category> findByName(String name);
}
