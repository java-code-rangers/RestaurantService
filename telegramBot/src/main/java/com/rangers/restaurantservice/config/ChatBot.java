package com.rangers.restaurantservice.config;

import com.rangers.restaurantservice.dto.UserDto;
import com.rangers.restaurantservice.sevice.UpdateHandlerService;
import com.rangers.restaurantservice.service.impl.UserServiceImpl;
import com.rangers.restaurantservice.userVarieble.UserVariable;
import com.rangers.restaurantservice.util.BotUtils;
import com.rangers.restaurantservice.util.GetButtons;
import com.rangers.restaurantservice.util.RegistrationUser;
import com.rangers.restaurantservice.util.headers.MenuHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.rangers.restaurantservice.util.BotUtils.*;

@Component
@Slf4j
public class ChatBot extends TelegramLongPollingBot {
    private final BotConfig config;
    public static final Map<String, UserVariable> userVariableMap = new ConcurrentHashMap<>();
    private final UserServiceImpl userService;
    private final RegistrationUser registrationUser;
    private final UpdateHandlerService updateHandlerService;

    @Autowired
    public ChatBot(BotConfig config, UserServiceImpl userService, RegistrationUser registrationUser, UpdateHandlerService updateHandlerService) {
        super(config.getBotToken());
        this.config = config;
        this.userService = userService;
        this.registrationUser = registrationUser;
        this.updateHandlerService = updateHandlerService;
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                handleIncomingMessage(update);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        } else if (update.hasMessage() && update.getMessage().hasLocation()) {
            String chatId = update.getMessage().getChatId().toString();
//            handleReceivedLocation(chatId, update.getMessage().getLocation());
        }
    }

    /**
     * Handles the initial start action for specialization selection or if the user is not registered
     * starts registration.
     *
     * @param chatId the chat ID of the user.
     * @author Viktor
     */
    private void handleStart1(String chatId) {
        if (registrationUser.isHaveUser(chatId)) {
            sendMenu(chatId, GetButtons.getListsProductMenu(), MenuHeader.CHOOSE_ACTION);
        } else {
            userVariableMap.putIfAbsent(chatId, new UserVariable());
            userVariableMap.get(chatId).setUserRegistrationDto(new UserDto());
            userVariableMap.get(chatId).setRegistrationStep(0);
            try {
                execute(updateHandlerService.startRegistration(chatId));
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
    }



    /**
     * Deletes a message in the chat by its ID.
     *
     * @param chatId    the chat ID of the user.
     * @param messageId the ID of the message to be deleted.
     * @author Viktor
     */
    private void deleteMessage(String chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }

    }

    private void handleIncomingMessage(Update update) throws TelegramApiException {
        String chatId = String.valueOf(update.getMessage().getChatId());
        String messageText = update.getMessage().getText();

        userVariableMap.computeIfAbsent(chatId, k -> new UserVariable());
        Object response = updateHandlerService.roleDivision(chatId,messageText);
        try {
            if (userVariableMap.get(chatId).getIsRegistrationInProgress().equals(true)) {
                execute(updateHandlerService.handleRegistration(messageText, chatId));
            }
            deleteMessage(chatId, userVariableMap.get(chatId).getLastMessageId());
        }catch (Exception e) {
            log.info(e.getMessage());
        }
        if (response instanceof BotApiMethod<?>){
            Message sendMessage = (Message) execute((BotApiMethod<?>)response);
            userVariableMap.get(chatId).setLastMessageId(sendMessage.getMessageId());
        }else if (response instanceof SendPhoto){
            Message message = execute((SendPhoto) response);
            userVariableMap.get(chatId).setLastMessageId(message.getMessageId());
        }



}

public void handleCallbackQuery(Update update) {
    String chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
    String callbackData = update.getCallbackQuery().getData();
    if (callbackData == null) {
        sendMessage(chatId, "Callback data is null.");
    }
    assert callbackData != null;
    handleDefaultCallback(chatId, callbackData);
}


/**
 * Handles default callback actions based on the provided callback data.
 *
 * @param chatId       the chat ID of the user.
 * @param callbackData the callback data containing the default action.
 */
public void handleDefaultCallback(String chatId, String callbackData) {
    switch (callbackData) {
        case "start1":
            handleStart1(chatId);
            break;
        case "start2":
            sendMenu(chatId, GetButtons.getListsOrderMenu(), MenuHeader.CHOOSE_ACTION);
            break;
        case "owner1":
            sendMenu(chatId, GetButtons.getListsProductCatalogOwnerMenu(), MenuHeader.CHOOSE_ACTION);
            break;
        case "WorkingWithCategories":
            sendMenu(chatId, GetButtons.getListsWorkingWithCategoriesOwnerMenu(), MenuHeader.CHOOSE_ACTION);
            break;
        case "WorkingWithProduct":
            sendMenu(chatId, GetButtons.getListsProductOwnerMenu(), MenuHeader.CHOOSE_ACTION);
            break;
        case "AddingProduct":
            sendMsg(chatId,"Adding product.");
            break;
        case "owner2":
            sendMenu(chatId, GetButtons.getListsNewsletterMenu(), MenuHeader.CHOOSE_ACTION);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + callbackData);
    }
}



/**
 * Sends a menu message to the user with the provided inline keyboard buttons and header.
 *
 * @param chatId     the chat ID of the user.
 * @param rowsInline the inline keyboard buttons.
 * @param header     the header text for the menu.
 * @author Viktor
 */
public void sendMenu(String chatId, List<List<InlineKeyboardButton>> rowsInline, String header) {
    SendMessage message = BotUtils.sendMenu(chatId, rowsInline, header);

    try {
        Message sentMessage = execute(message);
        int messageId = sentMessage.getMessageId();
        if (userVariableMap.get(chatId).getLastMessageId() != null) {
            deleteMessage(chatId, userVariableMap.get(chatId).getLastMessageId());
        }
        userVariableMap.get(chatId).setLastMessageId(messageId);
    } catch (TelegramApiException ignored) {

    }
}

/**
 * Sends a text message to the user.
 *
 * @param chatId the chat ID of the user.
 * @param text   the message text.
 * @author Viktor
 */
public void sendMsg(String chatId, String text) {
    if (text != null) {
        SendMessage message = sendMessage(chatId, text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }
    }
}
}
