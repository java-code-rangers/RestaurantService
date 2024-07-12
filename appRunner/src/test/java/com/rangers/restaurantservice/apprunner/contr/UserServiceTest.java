package com.rangers.restaurantservice.apprunner.contr;

import com.rangers.restaurantservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void test() {
        System.out.println(userService.getUserByChatId("479322389"));
    }
}
