package com.rangers.restaurantservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserDto {
    @JsonSerialize(using = ToStringSerializer.class)
    ObjectId id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String role;
    String chatId;

}

