package com.rangers.restaurantservice.apprunner.impl;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void getUserByChatIdTest() {
    }

    @Test
    void createUser() {
    }
}