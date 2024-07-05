package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rangers.restaurantservice.entity.Order;
import org.bson.types.ObjectId;


public record ResponseReviewDto(@JsonSerialize(using = ToStringSerializer.class)
                                ObjectId id, UserDto user, Order order,
                                String description) {
}
