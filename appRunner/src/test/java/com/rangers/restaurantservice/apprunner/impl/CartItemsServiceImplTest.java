package com.rangers.restaurantservice.apprunner.impl;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.entity.CartItems;
import com.rangers.restaurantservice.mapper.CartItemsMapper;
import com.rangers.restaurantservice.repository.CartItemsRepository;
import com.rangers.restaurantservice.service.impl.CartItemsServiceImpl;
import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartItemsServiceImplTest {

    @Autowired
    CartItemsServiceImpl service;

    @Autowired
    CartItemsRepository repository;

    @Test
    public void findByIdPositiveTest() throws ObjectNotFoundException {
        CartItemsDto expected = getCartItemsDto();
        CartItemsDto actual = service.findById(new ObjectId("70d5a6e7b7f84512a22c15b3"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdNegativeTest() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> service.findById(new ObjectId("70d5a6e7b7f84512a22c15a0")));
    }

    @Test
    public void findAllTest() throws ObjectNotFoundException {
        List<CartItemsDto> expected = getCartItemsList();
        List<CartItemsDto> actual = service.findAll(new ObjectId("6681797250f67e6871d91579"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createTest() throws ObjectNotFoundException {
        CartItemsDto expected = service.create(
                new CartItemsDto(null, new ObjectId("66842a43c995c63518773a6f"), new ObjectId("6681797250f67e6871d91579"), 1));
        Assertions.assertNotNull(expected);
        service.delete(expected.getId());
    }


    @Test
    public void updateQuantityTest() throws ObjectNotFoundException {
        CartItemsDto expected = getCartItemsDto();
        service.updateQuantity(expected.getId(), 2);
        Assertions.assertEquals(2, expected.getQuantity());
    }

    @Test
    public void deleteAllByUserIdTest() {
        service.deleteAllByUserId(new ObjectId("6681797250f67e6871d91579"));
    }


    private CartItemsDto getCartItemsDto() throws ObjectNotFoundException {
        return service.findById(new ObjectId("70d5a6e7b7f84512a22c15b3"));
    }

    private List<CartItemsDto> getCartItemsList() throws ObjectNotFoundException {
        CartItemsDto cartItems1 = getCartItemsDto();
        CartItemsDto cartItems2 = service.findById(new ObjectId("70d5a6e7b7f84512a22c15b4"));
        CartItemsDto cartItems3 = service.findById(new ObjectId("70d5a6e7b7f84512a22c15b5"));
        return List.of(cartItems1, cartItems2, cartItems3);
    }
}
