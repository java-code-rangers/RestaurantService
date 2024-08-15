package com.rangers.restaurantservice.mapper;


import com.rangers.restaurantservice.dto.OrderDto;
import com.rangers.restaurantservice.entity.Order;
import com.rangers.restaurantservice.enums.Status;
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

//    @AfterMapping
//    default void toDto( @MappingTarget OrderDto orderDto) {
//        if (orderDto != null && orderDto.getCreatedAt() == null) {
//            orderDto.setCreatedAt(LocalDateTime.now());
//            orderDto.setUpdatedAt(LocalDateTime.now());
//            orderDto.setOrderDate(LocalDateTime.now());
//            orderDto.setStatus(String.valueOf(Status.PROCESSING));
//        }
//    }

    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
}
