package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.CartItems;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends MongoRepository<CartItems, ObjectId> {
    @Query("{ 'user': ?0 }")
    List<CartItems> findAllByUserId(ObjectId userId);

    @Query(value = "{ 'user': ?0 }", delete = true)
    void deleteAllByUserId(ObjectId userId);
}
