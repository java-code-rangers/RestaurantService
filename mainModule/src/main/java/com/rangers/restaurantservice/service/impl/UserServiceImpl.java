package com.rangers.restaurantservice.service.impl;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.entity.User;
import com.rangers.restaurantservice.enums.Role;
import com.rangers.restaurantservice.mapper.UserMapper;
import com.rangers.restaurantservice.repository.UserRepository;
import com.rangers.restaurantservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto getUserByChatId(String chatId) {
        return userMapper.toDto(userRepository.getUserByChatId(chatId));
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void setOwnerRole(String chatId) {
        User user = userRepository.getUserByChatId(chatId);
        user.setRole(Role.OWNER);
        userRepository.save(user);
    }

}
