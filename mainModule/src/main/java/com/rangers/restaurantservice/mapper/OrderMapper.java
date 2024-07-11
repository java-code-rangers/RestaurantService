package com.rangers.restaurantservice.mapper;


import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    OrderDto toDto(Order order);


    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);

    List<Order> toEntityList(List<OrderDto> orderDtos);

    OrderDto orderDetailsDtosToOrderDto(List<OrderDetailsDto> orderDetailsDto);
}
