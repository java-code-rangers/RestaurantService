package com.rangers.restaurantservice.mapper;


import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.dto.OrderDetailsDto;
import com.rangers.restaurantservice.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailsMapper {
    OrderDetailsDto toDto(OrderDetails orderDetails);
    OrderDetails toEntity(OrderDetailsDto orderDetailsDto);

    OrderDetailsDto careItemsToOrderDetailsDto(CartItemsDto cartItemsDto);
}
