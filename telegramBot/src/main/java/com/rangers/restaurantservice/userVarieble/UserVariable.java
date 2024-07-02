package com.rangers.restaurantservice.userVarieble;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class UserVariable {
    private UserDto userRegistrationDto;
    private Integer registrationStep;
    private Boolean isRegistrationInProgress;
    private Integer lastMessageId;
    private String userId;
    private Role userRole;
    private Map<Integer, String> historyCallbackData;
}
