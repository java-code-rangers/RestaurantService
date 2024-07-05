package com.rangers.restaurantservice.controller;

import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.service.CategoryService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    public String createCategory(@RequestBody Category category){
        ObjectId parentId = category.getParentId();
        String name = category.getName();
        return categoryService.createCategory(parentId, name);
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable String id) throws ObjectNotFoundException {
        return categoryService.getCategoryById(new ObjectId(id));
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("getByName/{name}")
    public Category getCategoryByName(@PathVariable String name) throws ObjectNotFoundException{
        return categoryService.getCategoryByName(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id,
                                   @RequestBody Category category) // В JSON указыываем только parentId и name
                                   throws ObjectNotFoundException{
        return categoryService.updateCategory(new ObjectId(id), category.getParentId(), category.getName());
    }

    @PutMapping("/activate/{id}")
    public Category activateOrDeactivateCategory(@PathVariable String id) throws ObjectNotFoundException{
        return categoryService.activateOrDeactivateCategory(new ObjectId(id));
    }
}
