package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class OrderDetailsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId odId;
    private Integer quantity;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId productId;
    private String productName;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId oderId;
}
