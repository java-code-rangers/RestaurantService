package com.rangers.restaurantservice.mapper;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.entity.User;
import com.rangers.restaurantservice.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = Role.class)
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "role", expression = "java(com.rangers.restaurantservice.enums.Role.CLIENT)")
    User toEntity(UserDto userDto);


}
