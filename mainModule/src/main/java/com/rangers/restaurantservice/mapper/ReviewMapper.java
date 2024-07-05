package com.rangers.restaurantservice.mapper;

import com.rangers.restaurantservice.dto.RequestReviewDto;
import com.rangers.restaurantservice.dto.ResponseReviewDto;
import com.rangers.restaurantservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    @Mapping(target = "user.role", source = "user.role")
    ResponseReviewDto toDto(Review review);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "user", ignore = true)
    Review toEntity(RequestReviewDto reviewRequestDto);

}
