package com.rangers.restaurantservice.service;

import com.rangers.restaurantservice.dto.UserDto;
import org.bson.types.ObjectId;

public interface UserService {
    UserDto getUserByChatId(String chatId);
    UserDto createUser(UserDto userDto);
    void setOwnerRole(String chatId);
}
