package com.rangers.restaurantservice.mapper;


import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.entity.OrderDetails;
import com.rangers.restaurantservice.repository.OrderDetailsRepository;
import com.rangers.restaurantservice.repository.OrderRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = OrderRepository.class)
public interface OrderDetailsMapper {

    @Mapping(target = "orderId", source = "order.orderId")
    OrderDetailsDto toDto(OrderDetails orderDetails);

    @Mapping(target = "order", expression = "java(orderRepository.findById(new ObjectId(String.valueOf(orderDetailsDto.getOrderId()))).orElseThrow())")
    OrderDetails toEntity(OrderDetailsDto orderDetailsDto, OrderRepository orderRepository);

    default List<OrderDetails> toEntityList(List<OrderDetailsDto> orderDetailsDtoList, OrderRepository orderRepository) {
        return orderDetailsDtoList.stream()
                .map(orderDetailsDto -> toEntity(orderDetailsDto, orderRepository))
                .toList();
    }


    OrderDetailsDto cartItemsToOrderDetailsDto(CartItemsDto cartItemsDto);

}
