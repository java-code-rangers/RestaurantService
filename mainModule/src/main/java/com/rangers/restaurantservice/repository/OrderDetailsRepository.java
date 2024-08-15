package com.rangers.restaurantservice.repository;

import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.entity.OrderDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails, ObjectId> {
    List<OrderDetails> findAllByProductId(ObjectId productId);


}
