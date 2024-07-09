package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.enums.Status;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface OrderService {
    OrderDto findById(ObjectId id) throws ObjectNotFoundException;

    List<OrderDto> findAll();

    List<OrderDto> findAllByUserId(ObjectId userId);

    List<OrderDto> findAllByCategory(Category category);

    OrderDto create(List<CartItemsDto> cartItemsDtos);

    void update(ObjectId id, Status status) throws ObjectNotFoundException;

    List<OrderDto> findOrderByStatus(Status status);

}
