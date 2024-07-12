package com.rangers.restaurantservice.userVarieble;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Map;
@Getter
@Setter
public class UserVariable {
    private UserDto userRegistrationDto;
    private Integer registrationStep;
    private Boolean isRegistrationInProgress;
    private Integer lastMessageId;
    private ObjectId userId;
    private Role userRole;
    private Map<Integer, String> historyCallbackData;
}
