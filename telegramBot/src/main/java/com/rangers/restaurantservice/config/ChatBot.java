package com.rangers.restaurantservice.config;

import com.rangers.restaurantservice.interf.UpdateHandlerService;
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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class ChatBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private UpdateHandlerService updateHandlerService;

    @Autowired
    public ChatBot(BotConfig config) {
        super(config.getBotToken());
        this.config = config;
    }

    @Autowired
    public void setUpdateHandlerService(UpdateHandlerService updateHandlerService) {
        this.updateHandlerService = updateHandlerService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Object response = updateHandlerService.handleUpdate(update);
        try {
            if (response instanceof BotApiMethod<?>) {
                execute((BotApiMethod<?>) response);
            } else if (response instanceof SendPhoto) {
                execute((SendPhoto) response);
            }
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
