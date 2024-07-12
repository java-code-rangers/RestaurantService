package com.rangers.restaurantservice.mapper;

import com.rangers.restaurantservice.dto.CartItemsDto;
import com.rangers.restaurantservice.entity.CartItems;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartItemsMapper {
    CartItemsDto toDto(CartItems cartItems);
    CartItems toEntity(CartItemsDto cartItemsDto);

    List<CartItemsDto> toDtoList(List<CartItems> cartItemsList);
}
