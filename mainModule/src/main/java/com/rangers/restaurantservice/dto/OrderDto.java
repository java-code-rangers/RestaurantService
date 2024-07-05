package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rangers.restaurantservice.entity.OrderDetails;
import lombok.Data;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId orderId;
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal sum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderDetailsDto> orderDetailsDtoList;
}
