package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.CartItems;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartItemsRepository extends MongoRepository<CartItems, ObjectId> {
    @Query("{ 'user': ?0 }")
    public List<CartItems> findAllByUserId(ObjectId userId);

    @Query(value = "{ 'user': ?0 }", delete = true)
    void deleteAllByUserId(ObjectId userId);
}
