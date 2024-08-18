package com.rangers.restaurantservice.mapper;


import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Order;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class, OrderDetailsMapper.class})
public interface OrderMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "orderDate", source = "orderDate")
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "orderDetailsDtoList", source = "orderDetailsList")
    OrderDto toDto(Order order);

    @AfterMapping
    default void toDto(@MappingTarget OrderDto orderDto, Order order) {
        OrderDetailsMapper odm = new OrderDetailsMapperImpl();

        orderDto.setOrderDetailsDtoList(order.getOrderDetailsList().stream()
                .map(odm::toDto)
                .toList());
    }

    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
}
