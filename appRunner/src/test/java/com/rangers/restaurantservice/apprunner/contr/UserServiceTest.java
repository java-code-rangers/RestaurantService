package com.rangers.restaurantservice.apprunner.contr;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void test() {
        UserDto userDtoExpected = new UserDto();
        userDtoExpected.setFirstName("Viktor");
        userDtoExpected.setLastName("Bulatov");
        userDtoExpected.setEmail("testemail@gmail.com");
        userDtoExpected.setPhoneNumber("4738858588388");
        userDtoExpected.setRole("OWNER");
        userDtoExpected.setChatId("479322389");
        UserDto userDtoActual = userService.getUserByChatId("479322389");
        Assertions.assertEquals(userDtoExpected, userDtoActual);
    }
}
