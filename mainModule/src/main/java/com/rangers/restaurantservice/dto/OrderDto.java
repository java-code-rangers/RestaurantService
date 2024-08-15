package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal sum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime updatedAt;
    private List<OrderDetailsDto> orderDetailsDtoList;
}
