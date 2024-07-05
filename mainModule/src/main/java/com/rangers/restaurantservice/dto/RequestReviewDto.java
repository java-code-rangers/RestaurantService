package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class RequestReviewDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId orderId;
    private String description;
}
