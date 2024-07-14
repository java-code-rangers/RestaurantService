package com.rangers.restaurantservice.apprunner.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.repository.CategoryRepository;
import com.rangers.restaurantservice.service.impl.CategoryServiceImpl;
import jakarta.ws.rs.BadRequestException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getCategoryByIdPositiveTest() throws ObjectNotFoundException {
        Category expected = getExpected();
        Category actual = categoryService.getCategoryById(new ObjectId("6685d4d6d204885cfc728065"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCategoryByIdNegativeTest() throws ObjectNotFoundException {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> categoryService.getCategoryById(new ObjectId("6685d4d6d204885cfc728066")));
    }

    @Test
    void getAllCategoryTest() {
        List<Category> expected = getExpectedList();
        List<Category> actual = categoryService.getAllCategory();
        Assertions.assertTrue(expected.size() == actual.size() && expected.containsAll(actual));
    }

    @Test
    void getCategoryByNamePositiveTest() throws ObjectNotFoundException {
        Category expected = getExpected();
        Category actual = categoryService.getCategoryByName("cake");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCategoryByNameNegativeTest() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> categoryService.getCategoryByName("name"));
    }

    @Test
    void createCategoryPositiveTest() {
        // create category with parentId
        String result1 = categoryService.createCategory
                (new ObjectId("6685d5aed204885cfc728066"), "newCategory1");
        // create category without parentId
        String result2 = categoryService.createCategory(null, "newCategory2");
        Assertions.assertEquals("Category created", result1);
        Assertions.assertEquals("Category created", result2);
        categoryRepository.deleteByName("newCategory1");
        categoryRepository.deleteByName("newCategory2");
    }

    @Test
    void createCategoryNegativeTest() {
        String result1 = categoryService.createCategory
                (new ObjectId("6685d5aed204885cfc728066"), "biscuit");
        String result2 = categoryService.createCategory
                (new ObjectId("6685d5aed204885cfc728061"), "newCategory");
        Assertions.assertEquals("Category already exists", result1);
        Assertions.assertEquals("There is no such parentId", result2);
    }

    @Test
    void updateCategoryPositiveTest() throws ObjectNotFoundException {
        Category before = categoryRepository.getCategoryById(new ObjectId("6685da712dcd944ce25951fb"));
        Category updated = categoryService.updateCategory(new ObjectId("6685da712dcd944ce25951fb"),
                new ObjectId("6685d4d6d204885cfc728065"), "sweet biscuit");
        Category after = categoryRepository.getCategoryById(new ObjectId("6685da712dcd944ce25951fb"));
        Assertions.assertNotEquals(before.getName(), after.getName());
        Assertions.assertEquals(updated.getName(), after.getName());
        categoryService.updateCategory(new ObjectId("6685da712dcd944ce25951fb"),
                new ObjectId("6685d4d6d204885cfc728065"), "biscuit");
    }

    @Test
    void updateCategoryNegativeTest() {
        Assertions.assertThrows(BadRequestException.class, () -> categoryService.updateCategory
                (new ObjectId("6685da712dcd944ce25951fb"),
                 new ObjectId("6685da712dcd944ce25951fb"),
                  "new biscuit"));
    }

    @Test
    void activateOrDeactivateCategory() throws ObjectNotFoundException {
        ObjectId id = new ObjectId("6685da712dcd944ce25951fb");
        Category before = categoryRepository.getCategoryById(id);
        categoryService.activateOrDeactivateCategory(id);
        Category after = categoryRepository.getCategoryById(id);
        Assertions.assertNotSame(before.getIsActive(), after.getIsActive());
    }

    private Category getExpected() {
        Category category = new Category();
        category.setId(new ObjectId("6685d4d6d204885cfc728065"));
        return category;
    }

    private List<Category> getExpectedList() {
        Category category1 = getExpected();
        Category category2 = new Category();
        Category category3 = new Category();
        Category category4 = new Category();
        category2.setId(new ObjectId("6685d5aed204885cfc728066"));
        category3.setId(new ObjectId("6685da712dcd944ce25951fa"));
        category4.setId(new ObjectId("6685da712dcd944ce25951fb"));
        return List.of(category1, category2, category3, category4);
    }
}