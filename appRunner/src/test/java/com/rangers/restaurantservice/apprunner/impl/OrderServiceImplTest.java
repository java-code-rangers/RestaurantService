package com.rangers.restaurantservice.apprunner.impl;


import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.enums.Status;
import com.rangers.restaurantservice.mapper.OrderDetailsMapper;
import com.rangers.restaurantservice.repository.OrderDetailsRepository;
import com.rangers.restaurantservice.repository.OrderRepository;
import com.rangers.restaurantservice.service.impl.CartItemsServiceImpl;
import com.rangers.restaurantservice.service.impl.OrderServiceImpl;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl service;

    @Autowired
    CartItemsServiceImpl cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderDetailsMapper orderDetailsMapper;

    @Test
    public void findByIdTest() throws ObjectNotFoundException {
        OrderDto expected = getOrderDto();
        OrderDto actual = service.findById(new ObjectId("71d5a6e7b7f84512a22c15b1"));
        System.out.println(actual.toString());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAllTest() {
        System.out.println(service.findAll());

    }

    @Test
    public void findAllByUserIdTest() {
        System.out.println(service.findAllByUserId(new ObjectId("6681797250f67e6871d91579")));
    }

    @Test
    public void findAllByCategoryTest() throws ObjectNotFoundException {
        System.out.println(service.findAllByCategory(new ObjectId("6685d4d6d204885cfc728065")));
    }

    @Test
    public void findOrderByStatusTest() throws ObjectNotFoundException {

        System.out.println(service.findOrderByStatus(Status.CANCELED));
    }

    @Test
    public void updateTest() throws ObjectNotFoundException {
        service.update(getOrderDto().getOrderId(), Status.NEW);
        OrderDto orderDto = getOrderDto();
        Assertions.assertEquals(String.valueOf(Status.NEW), orderDto.getStatus());
    }

    @Test
    public void createTest() throws ObjectNotFoundException {
        OrderDto orderDto = service.create(getCartItemsDtoList());
        System.out.println(orderDto);
    }

    private OrderDto getOrderDto() throws ObjectNotFoundException {
        return service.findById(new ObjectId("71d5a6e7b7f84512a22c15b1"));
    }

    private List<CartItemsDto> getCartItemsDtoList() throws ObjectNotFoundException {
        CartItemsDto cartItems1 = cartService.findById(new ObjectId("70d5a6e7b7f84512a22c15b3"));
        CartItemsDto cartItems2 = cartService.findById(new ObjectId("70d5a6e7b7f84512a22c15b4"));
        CartItemsDto cartItems3 = cartService.findById(new ObjectId("70d5a6e7b7f84512a22c15b5"));
        return List.of(cartItems1, cartItems2, cartItems3);
    }

}
