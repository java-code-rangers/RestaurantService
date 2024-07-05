package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import java.math.BigDecimal;

@Data
public class ProductDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId productId;
    private String name;
    private String description;
    private BigDecimal price;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId categoryId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    private String imageLink;
    private Boolean isActive;
}
