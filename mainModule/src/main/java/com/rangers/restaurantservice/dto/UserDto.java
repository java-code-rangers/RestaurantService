package com.rangers.restaurantservice.dto;

import lombok.Data;

@Data
public class UserDto {
    String id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String role;
    String chatId;

}

