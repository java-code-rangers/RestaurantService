package com.rangers.restaurantservice.impl;

import com.rangers.restaurantservice.interf.UpdateHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.rangers.restaurantservice.util.BotUtils.*;
@RequiredArgsConstructor
@Service
public class UpdateHandlerServiceImpl implements UpdateHandlerService {
    @Override
    public Object handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return  handleIncomingMessage(update);
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
        switch (messageText) {
            case "/start":
                return sendStartMessage(chatId);
            case "/menu":
                return sendStartMenu(chatId);
            case "/location":

                break;
            case "/support":

                break;
            case "/botinfo":
                break;
            case "/my_appointment":
                break;
            default:
//                if (userVariableMap.get(chatId).getIsRegistrationInProgress().equals(true)) {
//                    handleRegistration(messageText, chatId);
//                } else if (userVariableMap.get(chatId).getAddToCart().equals(true)) {
//                    handleQuantity(messageText, chatId, userVariableMap.get(chatId).getMedicineNameForCart());
//                } else if (userVariableMap.get(chatId).getIsSupportInProgress().equals(true)) {
//                    handleSupport(messageText, chatId);
//                } else if (userVariableMap.get(chatId).getIsChatInProgress().equals(true)) {
//                    handleAiMessage(chatId, messageText);
//                }
                break;
        }
        return sendMessage(update.getMessage().getChatId().toString(), "Please select an action");
    }

    private SendMessage handleCallbackQuery(Update update) {
        return null;
    }

    private SendMessage handleReceivedLocation(String chatId, Location location) {
        return null;
    }




}
