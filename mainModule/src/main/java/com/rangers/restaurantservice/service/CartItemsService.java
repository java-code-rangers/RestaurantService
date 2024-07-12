package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.CartItemsDto;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.types.ObjectId;

import java.util.List;

public interface CartItemsService {
    CartItemsDto findById(ObjectId id) throws ObjectNotFoundException;

    List<CartItemsDto> findAll(ObjectId userId);

    CartItemsDto create(CartItemsDto cartItemsDto) throws ObjectNotFoundException;

    CartItemsDto updateQuantity(ObjectId id, Integer quantity) throws ObjectNotFoundException;

    void deleteAllByUserId(ObjectId userId);

    void delete(ObjectId id) throws ObjectNotFoundException;
}
