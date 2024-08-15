package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId productId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    private Integer quantity;

}
