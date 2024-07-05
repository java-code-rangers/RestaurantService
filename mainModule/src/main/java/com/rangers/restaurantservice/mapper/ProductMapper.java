package com.rangers.restaurantservice.mapper;

import com.rangers.restaurantservice.dto.ProductDto;
import com.rangers.restaurantservice.entity.Product;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);
    List<ProductDto> toListDto(List<Product> products);

    Product toEntity(ProductDto productDto);

    @Mapping(target = "productId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ProductDto productDto, @MappingTarget Product product);
}
