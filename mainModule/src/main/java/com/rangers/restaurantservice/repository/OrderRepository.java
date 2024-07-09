package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.entity.CartItems;
import com.rangers.restaurantservice.entity.Category;
import com.rangers.restaurantservice.entity.Order;
import com.rangers.restaurantservice.enums.Status;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    @Query("{ 'user': ?0 }")
    List<Order> findAllByUserId(ObjectId userId);

    List<Order> findAllByStatus(Status status);

}
