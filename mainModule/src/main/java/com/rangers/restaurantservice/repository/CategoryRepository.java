package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {
    Category getCategoryByName(String categoryName);
    Category getCategoryById(ObjectId id);
}
