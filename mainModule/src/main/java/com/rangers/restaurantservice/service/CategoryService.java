package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.entity.Category;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(ObjectId id) throws ObjectNotFoundException;
    List<Category> getAllCategory();
    Category getCategoryByName(String name) throws ObjectNotFoundException;
    String createCategory(ObjectId parentId, String name);
    Category updateCategory(ObjectId id, ObjectId parentId, String name) throws ObjectNotFoundException;
    Category activateOrDeactivateCategory(ObjectId id) throws ObjectNotFoundException;
}
