package com.rangers.restaurantservice.sevice;

import com.rangers.restaurantservice.enums.Role;
import com.rangers.restaurantservice.service.UserService;
import com.rangers.restaurantservice.userVarieble.UserVariable;
import com.rangers.restaurantservice.util.GetButtons;
import com.rangers.restaurantservice.util.headers.MenuHeader;
import com.rangers.restaurantservice.validator.EmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

import static com.rangers.restaurantservice.config.ChatBot.userVariableMap;
import static com.rangers.restaurantservice.util.BotUtils.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UpdateHandlerService {

    private final UserService userService;

    public Object roleDivision(String chatId, String messageText) {

        if (userVariableMap.get(chatId).getUserRole() == Role.CLIENT || userVariableMap.get(chatId).getUserRole() == null) {
            switch (messageText) {
                case "/start":
                    userVariableMap.get(chatId).setIsRegistrationInProgress(false);
                    return sendStartMessage(chatId);
                case "/menu":
                    return sendMenu(chatId, GetButtons.getListsStartMenu(), MenuHeader.CHOOSE_ACTION);
                case "/location":
                    break;
                case "/support":
                    break;
                case "/botinfo":
                    break;
                case "4dee3193-cda1-4457-9cad-22b794456fc0":
                    userService.setOwnerRole(chatId);
                    userVariableMap.get(chatId).setUserRole(Role.OWNER);
                    return sendStartOwnerMessage(chatId);
                default:
                    if (userVariableMap.get(chatId).getIsRegistrationInProgress().equals(true)) {
                    }
//                else if (userVariableMap.get(chatId).getAddToCart().equals(true)) {
//                    handleQuantity(messageText, chatId, userVariableMap.get(chatId).getMedicineNameForCart());
//                } else if (userVariableMap.get(chatId).getIsSupportInProgress().equals(true)) {
//                    handleSupport(messageText, chatId);
//                } else if (userVariableMap.get(chatId).getIsChatInProgress().equals(true)) {
//                    handleAiMessage(chatId, messageText);
//                }
            }
        } else if (userVariableMap.get(chatId).getUserRole() == Role.OWNER) {
            switch (messageText) {
                case "/start":
                    return sendStartOwnerMessage(chatId);
                case "/menu":
                    return sendMenu(chatId, GetButtons.getListsStartMenuOwner(), MenuHeader.CHOOSE_ACTION);
                case "/location":
                    break;
                case "/support":
                    break;
                case "/botinfo":
                    break;
                case "4dee3193-cda1-4457-9cad-22b794456fc0":
                    userVariableMap.get(chatId).setUserRole(Role.CLIENT);
                    return sendMenu(chatId, GetButtons.getListsStartMenu(), MenuHeader.CHOOSE_ACTION);
                default:
                    if (userVariableMap.get(chatId).getIsRegistrationInProgress().equals(true)) {
                    }
//                else if (userVariableMap.get(chatId).getAddToCart().equals(true)) {
//                    handleQuantity(messageText, chatId, userVariableMap.get(chatId).getMedicineNameForCart());
//                } else if (userVariableMap.get(chatId).getIsSupportInProgress().equals(true)) {
//                    handleSupport(messageText, chatId);
//                } else if (userVariableMap.get(chatId).getIsChatInProgress().equals(true)) {
//                    handleAiMessage(chatId, messageText);
//                }
            }
        }
        return null;

    }

    /**
     * Handles the user registration process step by step.
     *
     * @param messageText the message text from the user.
     * @param chatId      the chat ID of the user.
     * @author Viktor
     */
    public SendMessage handleRegistration(String messageText, String chatId){
        UserVariable userVariables = userVariableMap.get(chatId);

        if (userVariables == null) {
            return sendMessage(chatId, "Error: Registration not initialized.");
        }

        switch (Objects.requireNonNull(userVariables).getRegistrationStep()) {
            case 0:
                userVariables.getUserRegistrationDto().setFirstName(messageText);
                userVariables.setRegistrationStep(1);
                return sendMessage(chatId, "Great! Now enter your last name:");

            case 1:
                userVariables.getUserRegistrationDto().setLastName(messageText);
                userVariables.setRegistrationStep(2);
                return sendMessage(chatId, "Great! Now enter your email:");

            case 2:
                if (!EmailValidator.isValid(messageText)) {
                    System.out.println("Invalid email format for chatId " + chatId);
                    return sendMessage(chatId, "Invalid email format, please enter your email again!");
                } else {
                    userVariables.getUserRegistrationDto().setEmail(messageText);
                    userVariables.setRegistrationStep(3);
                    return sendMessage(chatId, "Great! Now enter your phone number:");
                }
            case 3:
                userVariables.getUserRegistrationDto().setPhoneNumber(messageText);
                userVariables.getUserRegistrationDto().setChatId(chatId);

                userService.createUser(userVariables.getUserRegistrationDto());
                userVariables.setIsRegistrationInProgress(false);
                userVariableMap.get(chatId).setUserRole(Role.CLIENT);
                userVariables.setUserId(getUserId(chatId));

                return sendMenu(chatId, GetButtons.getListsStartMenu(), MenuHeader.MENU_AFTER_REGISTRATION);
        }
        return null;
    }

    /**
     * Initiates the registration process by prompting the user to enter their first name.
     *
     * @param chatId the chat ID of the user.
     * @author Viktor
     */
    public SendMessage startRegistration(String chatId) {
        userVariableMap.get(chatId).setIsRegistrationInProgress(true);
        return sendMessage(chatId, "Hello! Let's start registration. Enter your name:");
    }
    private ObjectId getUserId(String chatId) {
        return userService.getUserByChatId(chatId).getId();
    }

}

