package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.repository.CategoryRepository;
import com.rangers.restaurantservice.service.CategoryService;
import jakarta.ws.rs.BadRequestException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(ObjectId id) throws ObjectNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) throws ObjectNotFoundException {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found"));
    }

    @Override
    @Transactional
    public String createCategory(ObjectId parentId, String name) {
        Category newCategory = new Category();
        try {
            getCategoryByName(name);
        } catch (ObjectNotFoundException e) {
            if (parentId != null) {
                try {
                    Category checkParent = getCategoryById(parentId);
                    newCategory.setParentId(checkParent.getId());
                } catch (ObjectNotFoundException ex) {
                    return "There is no such parentId";
                }
            }
            newCategory.setName(name);
            newCategory.setIsActive(true);
            categoryRepository.save(newCategory);
            return "Category created";
        }
        return "Category already exists";
    }

    @Override
    @Transactional
    public Category updateCategory(ObjectId id, ObjectId parentId, String name) throws ObjectNotFoundException {
        Category category = getCategoryById(id);
        if (parentId != null && parentId.equals(id)) throw new BadRequestException("Id can't be equals parentId");
        category.setName(name);
        category.setParentId(parentId);
        return categoryRepository.save(category);
    }

    @Override
    public Category activateOrDeactivateCategory(ObjectId id) throws ObjectNotFoundException {
        Category category = getCategoryById(id);
        category.setIsActive(!category.getIsActive());
        return categoryRepository.save(category);
    }
}
