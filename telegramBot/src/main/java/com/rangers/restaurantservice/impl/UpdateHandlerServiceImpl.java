package com.rangers.restaurantservice.impl;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.enums.Role;
import com.rangers.restaurantservice.interf.UpdateHandlerService;
import com.rangers.restaurantservice.service.impl.UserServiceImpl;
import com.rangers.restaurantservice.userVarieble.UserVariable;
import com.rangers.restaurantservice.util.GetButtons;
import com.rangers.restaurantservice.util.RegistrationUser;
import com.rangers.restaurantservice.util.headers.MenuHeader;
import com.rangers.restaurantservice.validator.EmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.rangers.restaurantservice.util.BotUtils.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UpdateHandlerServiceImpl implements UpdateHandlerService {

    private final Map<String, UserVariable> userVariableMap = new ConcurrentHashMap<>();
    private final UserServiceImpl userService;
    private final RegistrationUser registrationUser;

    @Override
    public Object handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return handleIncomingMessage(update);
        } else if (update.hasCallbackQuery()) {
            return handleCallbackQuery(update);
        } else if (update.hasMessage() && update.getMessage().hasLocation()) {
            String chatId = update.getMessage().getChatId().toString();
            return handleReceivedLocation(chatId, update.getMessage().getLocation());
        }

        return null;
    }

    private Object handleIncomingMessage(Update update) {
        String chatId = String.valueOf(update.getMessage().getChatId());
        String messageText = update.getMessage().getText();

        userVariableMap.computeIfAbsent(chatId, k -> new UserVariable());
        try {
            userVariableMap.get(chatId).setUserId(userService.getUserByChatId(chatId).getId());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        if (userVariableMap.get(chatId).getUserRole() == Role.CLIENT || userVariableMap.get(chatId).getUserRole() == null) {
            switch (messageText) {
                case "/start":
                    return sendStartMessage(chatId);
                case "/menu":
                    userVariableMap.get(chatId).setLastMessageId(update.getMessage().getMessageId());
                    return sendStartMenu(chatId);
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
                        return handleRegistration(messageText, chatId);
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
                    SendMessage newMenuMessage = sendStartMenu(chatId);
                    // Обновляем previousMenuMessageId на messageId нового меню
                    userVariableMap.get(chatId).setLastMessageId(update.getMessage().getMessageId());
                    return newMenuMessage;
                case "/location":
                    break;
                case "/support":
                    break;
                case "/botinfo":
                    break;
                case "4dee3193-cda1-4457-9cad-22b794456fc0":
                    userVariableMap.get(chatId).setUserRole(Role.CLIENT);
                    return sendStartMenu(chatId);
                default:
                    if (userVariableMap.get(chatId).getIsRegistrationInProgress().equals(true)) {
                        return handleRegistration(messageText, chatId);
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

        return deleteMessage(chatId, userVariableMap.get(chatId).getLastMessageId());
    }

    private SendMessage handleCallbackQuery(Update update) {
        String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        String callbackData = update.getCallbackQuery().getData();
        if (callbackData == null) {
            return sendMessage(chatId, "Callback data is null.");
        }
        return handleDefaultCallback(chatId, callbackData);
    }

    private SendMessage handleReceivedLocation(String chatId, Location location) {
        return null;
    }

    /**
     * Handles the initial start action for specialization selection or if the user is not registered
     * starts registration.
     *
     * @param chatId the chat ID of the user.
     * @author Viktor
     */
    private SendMessage handleStart1(String chatId) {
        if (registrationUser.isHaveUser(chatId)) {
            return sendMenu(chatId, GetButtons.getListsProductMenu(), MenuHeader.CHOOSE_ACTION);
        } else {
            userVariableMap.putIfAbsent(chatId, new UserVariable());
            userVariableMap.get(chatId).setUserRegistrationDto(new UserDto());
            userVariableMap.get(chatId).setRegistrationStep(0);
            return startRegistration(chatId);
        }
    }

    /**
     * Handles the user registration process step by step.
     *
     * @param messageText the message text from the user.
     * @param chatId      the chat ID of the user.
     * @author Viktor
     */
    private SendMessage handleRegistration(String messageText, String chatId) {
        UserVariable userVariables = userVariableMap.get(chatId);

        if (userVariables == null) {
            return sendMessage(chatId, "Ошибка: регистрация не инициализирована.");
        }

//        System.out.println("Current registration step for chatId " + chatId + ": " + userVariables.getRegistrationStep());

        switch (userVariables.getRegistrationStep()) {
            case 0:
                userVariables.getUserRegistrationDto().setFirstName(messageText);
                userVariables.setRegistrationStep(1);
//                System.out.println("Updated registration step to 1 for chatId " + chatId);
                return sendMessage(chatId, "Great! Now enter your last name:");
            case 1:
                userVariables.getUserRegistrationDto().setLastName(messageText);
                userVariables.setRegistrationStep(2);
//                System.out.println("Updated registration step to 2 for chatId " + chatId);
                return sendMessage(chatId, "Great! Now enter your email:");
            case 2:
                if (!EmailValidator.isValid(messageText)) {
                    System.out.println("Invalid email format for chatId " + chatId);
                    return sendMessage(chatId, "Invalid email format, please enter your email again!");
                } else {
                    userVariables.getUserRegistrationDto().setEmail(messageText);
                    userVariables.setRegistrationStep(3);
//                    System.out.println("Updated registration step to 3 for chatId " + chatId);
                    return sendMessage(chatId, "Great! Now enter your phone number:");
                }
            case 3:
                userVariables.getUserRegistrationDto().setPhoneNumber(messageText);
                userVariables.getUserRegistrationDto().setChatId(chatId);

                userService.createUser(userVariables.getUserRegistrationDto());
                userVariables.setIsRegistrationInProgress(false);
                userVariableMap.get(chatId).setUserRole(Role.CLIENT);
                userVariables.setUserId(userService.getUserByChatId(chatId).getId());
//                System.out.println("Registration completed for chatId " + chatId);
                sendMessage(chatId, "Great! Registration is completed!!!");
                return sendStartMenu(chatId);
        }

        return null;
    }

    /**
     * Initiates the registration process by prompting the user to enter their first name.
     *
     * @param chatId the chat ID of the user.
     * @author Viktor
     */
    private SendMessage startRegistration(String chatId) {
        userVariableMap.get(chatId).setIsRegistrationInProgress(true);
        return sendMessage(chatId, "Hello! Let's start registration. Enter your name:");
    }

    /**
     * Handles default callback actions based on the provided callback data.
     *
     * @param chatId       the chat ID of the user.
     * @param callbackData the callback data containing the default action.
     */
    private SendMessage handleDefaultCallback(String chatId, String callbackData) {
        return switch (callbackData) {
            case "start1" -> handleStart1(chatId);
            case "start2" -> sendMenu(chatId, GetButtons.getListsOrderMenu(), MenuHeader.CHOOSE_ACTION);
//            case "start3":
//                handleStart3(chatId);
//                break;
            default -> null;
        };
    }

    /**
     * Deletes a message in the chat by its ID.
     *
     * @param chatId    the chat ID of the user.
     * @param messageId the ID of the message to be deleted.
     * @author Viktor
     */
    private DeleteMessage deleteMessage(String chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        return deleteMessage;

    }
}

